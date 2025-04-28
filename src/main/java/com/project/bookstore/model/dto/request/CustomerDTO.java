package com.project.bookstore.model.dto.request;

public class CustomerDTO
{
    private String customerName;
    private String customerSurname;

    public CustomerDTO(String customerName, String customerSurname)
    {
        this.customerName = customerName;
        this.customerSurname = customerSurname;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getCustomerSurname()
    {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname)
    {
        this.customerSurname = customerSurname;
    }
}
