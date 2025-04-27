package com.project.bookstore.service.impl;

import com.project.bookstore.model.Book;
import com.project.bookstore.model.Customer;
import com.project.bookstore.model.dto.request.RequestPurchaseDTO;
import com.project.bookstore.model.dto.response.ResponsePurchaseDTO;
import com.project.bookstore.model.exceptions.BookException;
import com.project.bookstore.service.BookService;
import com.project.bookstore.service.CustomerService;
import com.project.bookstore.service.PricingService;
import com.project.bookstore.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService
{
    @Autowired
    private BookService bookService;

    @Autowired
    private PricingService pricingService;

    @Autowired
    private CustomerService customerService;

    @Override
    public ResponsePurchaseDTO processPurchase(RequestPurchaseDTO requestPurchaseDTO)
    {
        // is customer valid
        Customer customer = customerService.getCustomerById(requestPurchaseDTO.getCustomerId());

        // get all books - will make sure for validation also
        List<Book> bookList = findBooks(requestPurchaseDTO.getBookIdList());

        // calculate price through the method and update loyalty points
        Integer initialPoints = customer.getLoyalty();
        Double totalPrice = getPricingService().calculatePrice(bookList, customer, requestPurchaseDTO.isUseLoyaltyPoints());
        Integer pointsUsed = getUsedPoints(initialPoints, customer);
        Integer pointsEarned = bookList.size();

        customerService.updateCustomer(customer, customer.getId());

        return new ResponsePurchaseDTO(totalPrice, pointsUsed, pointsEarned);
    }

    private static Integer getUsedPoints(Integer initialPoints, Customer customer)
    {
        // Use 10 points if initialPoints >= 10 and customer has no loyalty points; otherwise use 0.
        return initialPoints >= 10 && customer.getLoyalty() == 0 ? 10 : 0;
    }

    private List<Book> findBooks(List<Long> bookIdList)
    {
        if (bookIdList.isEmpty())
        {
            throw new BookException("Book id list is empty");
        }

        List<Book> bookList = new ArrayList<>();

        for (Long id : bookIdList)
        {
            bookList.add(bookService.getBookById(id));
        }

        if (!bookList.isEmpty())
        {
            return bookList;
        }
        else
        {
            throw new BookException("Book list is empty");
        }
    }

    public BookService getBookService()
    {
        return bookService;
    }

    public PricingService getPricingService()
    {
        return pricingService;
    }

    public CustomerService getCustomerService()
    {
        return customerService;
    }
}
