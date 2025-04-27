package com.project.bookstore.service;

import com.project.bookstore.model.Book;
import com.project.bookstore.model.dto.request.BookDTO;

import java.util.List;

public interface BookService
{
    //Create
    Book addBook(BookDTO bookDTO);

    //Read
    Book getBookById(Long bookId);
    List<Book> getAllBooks();

    //Update
    Book updateBook(Book book, Long id);

    //Delete
    void deleteBook(Long bookId);
}
