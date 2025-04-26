package com.project.bookstore.service.impl;

import com.project.bookstore.model.Book;
import com.project.bookstore.model.dto.request.BookDTO;
import com.project.bookstore.model.eum.BookType;
import com.project.bookstore.model.exceptions.BookException;
import com.project.bookstore.repository.BookRepository;
import com.project.bookstore.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService
{
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(BookDTO bookDTO)
    {
        validateBookDTO(bookDTO);

        Book book = createBookModel(bookDTO);

        logger.info("Book entity created. Saving book in DB.");
        return getBookRepository().save(book);
    }

    /*
     * creating new object with the data from the dto
     */
    private Book createBookModel(BookDTO bookDTO)
    {
        Book book = new Book();

        book.setTitle(bookDTO.getTitle());
        book.setBasePrice(bookDTO.getBasePrice());
        book.setType(BookType.valueOf(bookDTO.getType().toUpperCase()));

        return book;
    }

    /*
    * making sure every input field that is necessary is present upon creating new entity
    */
    private void validateBookDTO(BookDTO bookDTO)
    {
        if (bookDTO == null)
            throw new BookException("BookDTO is null!");

        if (bookDTO.getTitle() == null || bookDTO.getTitle().isBlank())
            throw new BookException("Book title must not be null or blank!");

        if (bookDTO.getBasePrice() == null || bookDTO.getBasePrice() <= 0)
            throw new BookException("Book base price must be greater than zero!");

        if (bookDTO.getType() == null || bookDTO.getType().isBlank())
            throw new BookException("Book type must not be null or blank!");
    }

    @Override
    public Book getBookById(Long bookId)
    {
        logger.info("Listing book with id {}", bookId);
        return getBookRepository()
                .findById(bookId)
                .orElseThrow(() -> new BookException(String.format("Book with id %s not found!", bookId)));
    }

    @Override
    public List<Book> getAllBooks()
    {
        logger.info("Listing books");
        return getBookRepository().findAll();
    }

    @Override
    public Book updateBook(Book book)
    {
        logger.info("Updating book with id {}", book.getId());
        validateNewBook(book);

        Book existingBook = getBookById(book.getId());

        updateExistingBookData(book, existingBook);

        return getBookRepository().save(existingBook);
    }

    private void updateExistingBookData(Book book, Book existingBook)
    {
        existingBook.setTitle(book.getTitle());
        existingBook.setBasePrice(book.getBasePrice());
        existingBook.setType(book.getType());
    }

    private void validateNewBook(Book book)
    {
        if (book == null)
            throw new BookException("Book is null!");

        if (book.getId() == null)
            throw new BookException("Book id is null!");

        if (!getBookRepository().existsById(book.getId()))
            throw new BookException(String.format("Book with id %s not found!", book.getId()));
    }

    @Override
    public void deleteBook(Long bookId)
    {
        if (!getBookRepository().existsById(bookId))
            throw new BookException(String.format("Book with id %s not found!", bookId));

        logger.info("Deleting book with id {}", bookId);
        getBookRepository().deleteById(bookId);
    }

    public BookRepository getBookRepository()
    {
        return bookRepository;
    }
}
