package com.project.bookstore.service;

import com.project.bookstore.model.Customer;
import com.project.bookstore.model.dto.request.CustomerDTO;
import com.project.bookstore.model.dto.response.LoyaltyPointsDTO;

import java.util.List;

/**
 * Service interface for managing customer operations, including CRUD operations
 * and loyalty points management.
 *
 * <p>Operations provided:
 * <ul>
 *   <li>Create a new customer</li>
 *   <li>Retrieve customer by ID or list all customers</li>
 *   <li>Update existing customer</li>
 *   <li>Delete a customer</li>
 *   <li>Retrieve loyalty points for a customer</li>
 * </ul>
 */
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
