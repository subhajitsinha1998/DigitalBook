package com.subhajit251447.digitalbook.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subhajit251447.digitalbook.book.model.Book;
import com.subhajit251447.digitalbook.book.service.BookService;

@RestController
@RequestMapping(path = "/api/v1/digitalbooks")
@CrossOrigin(origins = "http://localhost:8080")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/search")
    public ResponseEntity<List<Book>> search(@RequestParam(required = false) String category, 
        @RequestParam(required = false) String title, @RequestParam(required = false) String author,
        @RequestParam(required = false) String price, @RequestParam(required = false) String publisher) {
            return ResponseEntity.ok(bookService.getBooks(category, title, author, price, publisher)); 
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @GetMapping(path = "/author/{author}/books")
    public ResponseEntity<List<Book>> getBooksByAuthorId(@PathVariable String author) {
            return ResponseEntity.ok(bookService.getBooksByAuthorId(author)); 
    }

    @PostMapping(path = "/author/{authorId}/books/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody(required = false) Book book, @PathVariable Integer authorId, 
        @PathVariable Integer bookId, @RequestParam(required = false) Boolean block) {
            ResponseEntity<Book> response = null;
            if (block == null) response = ResponseEntity.ok(bookService.updateBook(bookId, book));
            if (block != null) response = ResponseEntity.ok(bookService.updateBookStatus(bookId, block));
            return response;
    }

}
