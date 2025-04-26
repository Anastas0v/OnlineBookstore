package com.project.bookstore.web;

import com.project.bookstore.model.Customer;
import com.project.bookstore.model.dto.request.CustomerDTO;
import com.project.bookstore.model.dto.response.LoyaltyPointsDTO;
import com.project.bookstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerAPIController
{
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id)
    {
        Customer customer = getCustomerService().getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping({"/", "/all"})
    public ResponseEntity<List<Customer>> getAllCustomers()
    {
        List<Customer> customerList = getCustomerService().getAllCustomers();
        return ResponseEntity.ok(customerList);
    }

    @PostMapping("/save")
    public ResponseEntity<Customer> saveCustomer(@RequestBody CustomerDTO customerDTO)
    {
        Customer createdCustomer = getCustomerService().addCustomer(customerDTO);
        return ResponseEntity.ok(createdCustomer);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Long id)
    {
        Customer updatedCustomer = getCustomerService().updateCustomer(customer, id);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id)
    {
        getCustomerService().deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/loyalty-points")
    public ResponseEntity<LoyaltyPointsDTO> getLoyaltyPoints(@PathVariable Long id)
    {
        LoyaltyPointsDTO loyaltyPointsDTO = getCustomerService().getLoyaltyPointsForCustomer(id);
        return ResponseEntity.ok(loyaltyPointsDTO);
    }

    public CustomerService getCustomerService()
    {
        return customerService;
    }
}
