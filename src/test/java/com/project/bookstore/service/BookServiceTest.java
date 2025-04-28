package com.project.bookstore.service;

import com.project.bookstore.model.Book;
import com.project.bookstore.model.dto.request.BookDTO;
import com.project.bookstore.model.exceptions.BookException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
* Simple testing for the book service crud operations
*/

@SpringBootTest
class BookServiceTest
{
    @Autowired
    private BookService bookService;

    @Test
    void testAddBook_Success()
    {
        BookDTO bookDTO = new BookDTO("Test Book", 20.0, "regular");
        Book result = getBookService().addBook(bookDTO);

        assertNotNull(result);
    }

    @Test
    void testAddBook_NullBookDTO()
    {
        assertThrows(BookException.class, () -> getBookService().addBook(null));
    }

    @Test
    void testAddBook_BlankTitle()
    {
        BookDTO bookDTO = new BookDTO("", 20.0, "old_edition");
        assertThrows(BookException.class, () -> getBookService().addBook(bookDTO));
    }

    @Test
    void testUpdateBook_InvalidBook()
    {
        assertThrows(BookException.class, () -> getBookService().updateBook(null, 1L));
    }

    @Test
    void testUpdateBook_IdMismatch()
    {
        Book book = new Book();
        book.setId(null);
        assertThrows(BookException.class, () -> getBookService().updateBook(book, 1L));
    }

    public BookService getBookService()
    {
        return bookService;
    }
}
