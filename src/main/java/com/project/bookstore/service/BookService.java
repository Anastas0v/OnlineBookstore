package com.project.bookstore.service;

import com.project.bookstore.model.Book;
import com.project.bookstore.model.dto.request.BookDTO;

import java.util.List;

/**
 * Service interface for managing book operations, including CRUD functionality.
 *
 * <p>Operations provided:
 * <ul>
 *   <li>Create a new book</li>
 *   <li>Retrieve a book by its ID or list all books</li>
 *   <li>Update an existing book's details</li>
 *   <li>Delete a book by its ID</li>
 * </ul>
 */
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
