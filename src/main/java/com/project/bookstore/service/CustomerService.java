package com.project.bookstore.service;

import com.project.bookstore.model.Customer;
import com.project.bookstore.model.dto.request.CustomerDTO;
import com.project.bookstore.model.dto.response.LoyaltyPointsDTO;

import java.util.List;

public interface CustomerService
{
    //Create
    Customer addCustomer(CustomerDTO customerDTO);

    //Read
    Customer getCustomerById(Long customerId);
    List<Customer> getAllCustomers();

    //Update
    Customer updateCustomer(Customer customer, Long customerId);

    //Delete
    void deleteCustomer(Long customerId);

    //Get Loyalty Points For Customer
    LoyaltyPointsDTO getLoyaltyPointsForCustomer(Long customerId);
}
