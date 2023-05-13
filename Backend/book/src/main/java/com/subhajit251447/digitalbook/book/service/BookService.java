package com.subhajit251447.digitalbook.book.service;

import java.util.List;

import com.subhajit251447.digitalbook.book.model.Book;

public interface BookService {

    public List<Book> getBooks(String category, String title, String author,
        String price, String publisher);
    public Book createBook(Book book);
    public List<Book> getBooksByAuthorId(String author);
    public Book updateBook(Integer bookId, Book book);
    public Book updateBookStatus(Integer bookId, Boolean block);
    
}
