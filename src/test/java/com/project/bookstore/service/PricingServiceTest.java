package com.project.bookstore.service;
import com.project.bookstore.model.Book;
import com.project.bookstore.model.Customer;
import com.project.bookstore.model.dto.request.BookDTO;
import com.project.bookstore.model.dto.request.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PricingServiceTest
{
    @Autowired
    private PricingService pricingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    private Customer customer;
    private Book newReleaseBook;
    private Book regularBook;
    private Book oldEditionBook;

    // create data before reach test
    @BeforeEach
    public void setUp()
    {
        CustomerDTO customerDTO = new CustomerDTO("CustomerName", "CustomerSurname");
        BookDTO newReleaseBookDTO = new BookDTO("New Release", 100.0, "new_release");
        BookDTO regularBookDTO = new BookDTO("Regular Book", 100.0, "regular");
        BookDTO oldEditionBookDTO = new BookDTO("Old Edition", 100.0, "old_edition");

        saveData(customerDTO, newReleaseBookDTO, regularBookDTO, oldEditionBookDTO);
    }

    private void saveData(CustomerDTO customerDTO, BookDTO newReleaseBookDTO, BookDTO regularBookDTO, BookDTO oldEditionBookDTO)
    {
        customer = getCustomerService().addCustomer(customerDTO);
        newReleaseBook = getBookService().addBook(newReleaseBookDTO);
        oldEditionBook = getBookService().addBook(oldEditionBookDTO);
        regularBook = getBookService().addBook(regularBookDTO);
    }

    /*
     * Buy one new release book, without loyalty points
     * calculate the price. it is not bundle since it is one book and we won't use loyalty points
     * verify: price is 100, and 1 point earned
     */
    @Test
    public void testSingleNew_ReleaseBook()
    {
        List<Book> books = Arrays.asList(newReleaseBook);
        double totalPrice = getPricingService().calculatePrice(books, customer, false);

        assertEquals(100, totalPrice, 0.01);
        assertEquals(1, customer.getLoyalty());
    }

    /*
    * Buy one regular book, without loyalty points - since there is one book and not at least 3 books, discount of 10% not applied.
    * calculate the price. it is not bundle since it is one book and we won't use loyalty points
    * verify: price is 100, and 1 point earned
    */
    @Test
    public void testSingleRegularBook()
    {
        List<Book> books = Arrays.asList(regularBook);
        double totalPrice = getPricingService().calculatePrice(books, customer, false);

        assertEquals(100, totalPrice, 0.01);
        assertEquals(1, customer.getLoyalty());
    }

    /*
     * Buy one old edition book, without loyalty points - since it is an old book a discount of 20% will be applied.
     * calculate the price. it is not bundle since it is one book and we won't use loyalty points
     * verify: price is 80, and 1 point earned
     */
    @Test
    public void testSingleOld_EditionBook()
    {

        List<Book> books = Arrays.asList(oldEditionBook);
        double totalPrice = getPricingService().calculatePrice(books, customer, false);

        assertEquals(80, totalPrice, 0.01);
        assertEquals(1, customer.getLoyalty());
    }

    /*
     * Buy at least three (in this case three regular) books, for each book price will be 90$
     * verify: price is 270, and 3 points earned - no loyalty
     */
    @Test
    public void testBundleRegularBooks()
    {
        List<Book> books = Arrays.asList(regularBook, regularBook, regularBook);
        double totalPrice = getPricingService().calculatePrice(books, customer, false);

        assertEquals(270, totalPrice, 0.01);
        assertEquals(3, customer.getLoyalty());
    }

    /*
     * Buy three or more books (in this case three old edition books), price will be reduced by 20% for each book
     * and extra 5% for bundle (three or more books) - no loyalty
     * verify: price is 228, and 3 points earned
     */
    @Test
    public void testBundleOld_EditionBooks()
    {
        List<Book> books = Arrays.asList(oldEditionBook, oldEditionBook, oldEditionBook);
        double totalPrice = getPricingService().calculatePrice(books, customer, false);

        assertEquals(228, totalPrice, 0.01);
        assertEquals(3, customer.getLoyalty());
    }

    /*
     * Buy one regular book with customer that have 10 loyalty
     * use the loyalty points - can get one regular or old edition for free
     * verify: price is 0 and points reset to 0
     */
    @Test
    public void testWithLoyaltyPoints()
    {
        List<Book> books = Arrays.asList(regularBook);
        customer.setLoyalty(10);
        double totalPrice = getPricingService().calculatePrice(books, customer, true);

        assertEquals(0, totalPrice, 0.01);
        assertEquals(0, customer.getLoyalty());
    }

    /*
     * Buy one regular book with customer that have 10 loyalty
     * DON'T use the loyalty points
     * verify: price is 100 and points are 11
     */
    @Test
    public void testWithoutLoyaltyPoints()
    {
        List<Book> books = Arrays.asList(regularBook);
        customer.setLoyalty(10);
        double totalPrice = getPricingService().calculatePrice(books, customer, false);

        assertEquals(100, totalPrice, 0.01);
        assertEquals(11, customer.getLoyalty());
    }

    /*
     * Buy one new release book with customer that have 10 loyalty
     * use the loyalty points
     * verify: price is 100 and points are 11 - no free book because of new release book
     */
    @Test
    public void testNew_ReleaseWithLoyaltyPoints()
    {
        List<Book> books = Arrays.asList(newReleaseBook);
        customer.setLoyalty(10);
        double totalPrice = getPricingService().calculatePrice(books, customer, true);

        assertEquals(100, totalPrice, 0.01);
        assertEquals(11, customer.getLoyalty());
    }

    /*
     * Buy one regular book with customer that have 5 loyalty points
     * use the loyalty points
     * verify: price is 100 and points are 6 (5+1) - no free book because of insufficient loyalty points
     */
    @Test
    public void testInsufficientLoyaltyPoints()
    {
        List<Book> books = Arrays.asList(regularBook);
        customer.setLoyalty(5);
        double totalPrice = getPricingService().calculatePrice(books, customer, true);

        assertEquals(100, totalPrice, 0.01);
        assertEquals(6, customer.getLoyalty());
    }

    /*
     * Buy mixed books - one new release, regular and old edition book with customer that have 10 loyalty points
     * use the loyalty points - new release no discount, regular will be free because of the loyalty and for old edition
     * price will be 20% and additional 5% off due to bundle (3 or more books)
     * verify: price is 176 (NEW RELEASE 100 + REGULAR 0 + OLD EDITION 100 * 0.8 * 0.95) and points are 0 - due to them being used up
     */
    @Test
    public void testMixedBooksWithLoyaltyAndBundleDiscount()
    {
        List<Book> books = Arrays.asList(newReleaseBook, regularBook, oldEditionBook);
        customer.setLoyalty(10);
        double totalPrice = getPricingService().calculatePrice(books, customer, true);

        assertEquals(176, totalPrice, 0.01);
        assertEquals(0, customer.getLoyalty());
    }

    /*
     * No books
     * Use the loyalty points
     * verify: price is 100 and points are 10 (same as in the beginning of the purchase process)
     */
    @Test
    public void testEmptyBookList()
    {
        List<Book> books = Collections.emptyList();
        customer.setLoyalty(10);
        double totalPrice = getPricingService().calculatePrice(books, customer, true);

        assertEquals(0, totalPrice, 0.01);
        assertEquals(10, customer.getLoyalty());
    }

    public PricingService getPricingService()
    {
        return pricingService;
    }

    public CustomerService getCustomerService()
    {
        return customerService;
    }

    public BookService getBookService()
    {
        return bookService;
    }
}
