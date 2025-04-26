package com.project.bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "customer_name")
    private String name;

    @Column(nullable = false, name = "customer_surname")
    private String surname;

    @Column(name = "customer_loyaltyPoints")
    private Integer loyalty = 0;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public Integer getLoyalty()
    {
        return loyalty;
    }

    public void setLoyalty(Integer loyalty)
    {
        this.loyalty = loyalty;
    }
}
