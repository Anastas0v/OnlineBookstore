package com.project.bookstore.service;

import com.project.bookstore.model.Book;
import com.project.bookstore.model.dto.request.BookDTO;
import com.project.bookstore.model.exceptions.BookException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    void testGetBookById_Success()
    {
        BookDTO bookDTO = new BookDTO("Get By ID Book", 30.0, "new_release");
        Book savedBook = getBookService().addBook(bookDTO);

        Book fetchedBook = getBookService().getBookById(savedBook.getId());

        assertNotNull(fetchedBook);
        assertEquals(savedBook.getId(), fetchedBook.getId());
    }

    @Test
    void testGetBookById_NotFound()
    {
        assertThrows(BookException.class, () -> getBookService().getBookById(999999L));
    }

    @Test
    void testGetAllBooks()
    {
        List<Book> books = getBookService().getAllBooks();

        assertNotNull(books);
        assertTrue(books.size() >= 0);
    }

    @Test
    void testUpdateBook_Success()
    {
        BookDTO bookDTO = new BookDTO("Original Title", 50.0, "regular");
        Book createdBook = getBookService().addBook(bookDTO);

        createdBook.setTitle("Updated Title");
        createdBook.setBasePrice(60.0);

        Book updatedBook = getBookService().updateBook(createdBook, createdBook.getId());

        assertEquals("Updated Title", updatedBook.getTitle());
        assertEquals(60.0, updatedBook.getBasePrice());
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

    @Test
    void testDeleteBook_Success()
    {
        BookDTO bookDTO = new BookDTO("Delete Test Book", 15.0, "regular");
        Book createdBook = getBookService().addBook(bookDTO);

        assertDoesNotThrow(() -> getBookService().deleteBook(createdBook.getId()));
    }

    @Test
    void testDeleteBook_NotFound()
    {
        assertThrows(BookException.class, () -> getBookService().deleteBook(888888L));
    }

    public BookService getBookService()
    {
        return bookService;
    }
}
