package com.project.bookstore.service;

import com.project.bookstore.model.Book;
import com.project.bookstore.model.Customer;

import java.util.List;

public interface PricingService
{
    double calculatePrice(List<Book> bookList, Customer customer, boolean useLoyaltyPoints);
}
