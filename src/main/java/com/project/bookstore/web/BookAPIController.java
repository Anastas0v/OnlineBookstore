package com.project.bookstore.web;

import com.project.bookstore.model.Book;
import com.project.bookstore.model.dto.request.BookDTO;
import com.project.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookAPIController
{
    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id)
    {
        Book book = getBookService().getBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping({"/", "/all"})
    public ResponseEntity<List<Book>> getAllBooks()
    {
        List<Book> bookList = getBookService().getAllBooks();
        return ResponseEntity.ok(bookList);
    }

    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(@RequestBody BookDTO bookDTO)
    {
        Book createdBook = getBookService().addBook(bookDTO);
        return ResponseEntity.ok(createdBook);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable Long id)
    {
        Book updatedBook = getBookService().updateBook(book, id);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id)
    {
        getBookService().deleteBook(id);
        return ResponseEntity.ok().build();
    }

    public BookService getBookService()
    {
        return bookService;
    }
}
