package com.project.bookstore.service.impl;

import com.project.bookstore.model.Book;
import com.project.bookstore.model.Customer;
import com.project.bookstore.model.eum.BookType;
import com.project.bookstore.service.PricingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingServiceImpl implements PricingService
{
    @Override
    public double calculatePrice(List<Book> bookList, Customer customer, boolean useLoyaltyPoints)
    {
        double totalPrice = 0;

        int earnedLoyaltyPoints = bookList.size();
        boolean hasBundle = bookList.size() >= 3;
        boolean canUsePoints = useLoyaltyPoints && customer.getLoyalty() >= 10;
        boolean appliedFreeBook = false;

        boolean canHaveBookForFree = canUsePoints && bookList.stream()
                .anyMatch(book -> book.getType() != BookType.NEW_RELEASE);

        for (Book book : bookList)
        {
            double bookPrice = book.getBasePrice();

            // old books get discount by 20
            if (book.getType() == BookType.OLD_EDITION)
            {
                bookPrice *= 0.8;
                // if has bundle apply 5% additional discount
                if (hasBundle)
                {
                    bookPrice *= 0.95;
                }
            }
            // regular books have regular price but if has bundle apply 10%
            // FOR NEW RELEASES NO DISCOUNT NO MATTER WHAT
            else if (book.getType() == BookType.REGULAR && hasBundle)
            {
                bookPrice *= 0.9;
            }

            // check if user can use loyalty points and if he wants to spend it
            // if user has more or equal 10 points, can have old release or regular for free

            if (canHaveBookForFree && !appliedFreeBook && book.getType() != BookType.NEW_RELEASE)
            {
                // set book price to 0 (free)
                bookPrice = 0;
                appliedFreeBook = true;
            }
            totalPrice += bookPrice;
        }

        if (canHaveBookForFree && appliedFreeBook)
        {
            customer.setLoyalty(0);
        }
        else
        {
            customer.setLoyalty(customer.getLoyalty() + earnedLoyaltyPoints);
        }

        return totalPrice;
    }
}
