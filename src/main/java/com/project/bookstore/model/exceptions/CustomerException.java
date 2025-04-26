package com.project.bookstore.model.exceptions;

public class CustomerException extends RuntimeException
{
    public CustomerException(String message)
    {
        super(message);
    }
}
