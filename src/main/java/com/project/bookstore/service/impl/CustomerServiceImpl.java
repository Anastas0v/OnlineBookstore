package com.project.bookstore.service.impl;

import com.project.bookstore.model.Customer;
import com.project.bookstore.model.dto.request.CustomerDTO;
import com.project.bookstore.model.dto.response.LoyaltyPointsDTO;
import com.project.bookstore.model.exceptions.CustomerException;
import com.project.bookstore.repository.CustomerRepository;
import com.project.bookstore.service.CustomerService;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class CustomerServiceImpl implements CustomerService
{
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(CustomerDTO customerDTO)
    {
        validateCustomerDTO(customerDTO);

        Customer customer = createCustomerModel(customerDTO);

        logger.info("Customer entity created. Saving customer in DB.");
        return getCustomerRepository().save(customer);
    }

    /*
     * creating new object with the data from the dto
     */
    private Customer createCustomerModel(CustomerDTO customerDTO)
    {
        Customer customer = new Customer();

        customer.setName(customerDTO.getCustomerName());
        customer.setSurname(customerDTO.getCustomerSurname());

        return customer;
    }

    /*
     * making sure every input field that is necessary is present upon creating new entity
     */
    private void validateCustomerDTO(CustomerDTO customerDTO)
    {
        if (customerDTO.getCustomerName() == null || customerDTO.getCustomerName().isEmpty())
            throw new CustomerException("Customer name cannot be empty");

        if (customerDTO.getCustomerSurname() == null || customerDTO.getCustomerSurname().isEmpty())
            throw new CustomerException("Customer surname cannot be empty");
    }

    @Override
    public Customer getCustomerById(Long customerId)
    {
        logger.info("Listing customer by id: {}", customerId);
        return getCustomerRepository()
                .findById(customerId)
                .orElseThrow(() -> new CustomerException(String.format("Customer with id %s not found", customerId)));
    }

    @Override
    public List<Customer> getAllCustomers()
    {
        logger.info("Listing all customers");
        return getCustomerRepository().findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer)
    {
        logger.info("Updating customer with id: {}", customer.getId());
        validateNewCustomer(customer);

        Customer existingCustomer = getCustomerById(customer.getId());

        updateExistingCustomerData(customer, existingCustomer);

        return getCustomerRepository().save(existingCustomer);
    }

    private void updateExistingCustomerData(Customer customer, Customer existingCustomer)
    {
        existingCustomer.setName(customer.getName());
        existingCustomer.setSurname(customer.getSurname());
    }

    private void validateNewCustomer(Customer customer)
    {
        if (customer == null)
            throw new CustomerException("Customer cannot be null");

        if (customer.getId() == null)
            throw new CustomerException("Customer id cannot be null");

        if (!getCustomerRepository().existsById(customer.getId()))
            throw new CustomerException(String.format("Customer with id %s not found", customer.getId()));
    }

    @Override
    public void deleteCustomer(Long customerId)
    {
        if (!getCustomerRepository().existsById(customerId))
            throw new CustomerException(String.format("Customer with id %s not found", customerId));

        logger.info("Deleting customer with id: {}", customerId);
        getCustomerRepository().deleteById(customerId);
    }

    @Override
    public LoyaltyPointsDTO getLoyaltyPointsForCustomer(Long customerId)
    {
        Customer customer = getCustomerById(customerId);
        return new LoyaltyPointsDTO(customerId, customer.getLoyalty());
    }
}
