package com.subhajit251447.digitalbook.book.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subhajit251447.digitalbook.book.exception.NotFoundException;
import com.subhajit251447.digitalbook.book.model.Book;
import com.subhajit251447.digitalbook.book.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    private void bookExists(Integer bookId) {
        if (!bookRepository.existsById(bookId)) 
                throw new NotFoundException("Book Not Found");
    }

    @Override
    public List<Book> getBooks(String category, String title, String author,
        String price, String publisher) {
            return bookRepository.customSearchBooks(category, title, author, price, publisher)
                .stream().filter(book->book.getActive()).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksByAuthorId(String author) {
            return bookRepository.customSearchBooks(null, null, author, null, null);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Integer bookId, Book book) {
        this.bookExists(bookId);
        book.setId(bookId);
        return bookRepository.save(book);
    }

    @Override
    public Book updateBookStatus(Integer bookId, Boolean block) {
        this.bookExists(bookId);
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            book.setActive(!block);
            book = bookRepository.save(book);
        }
        return book;
    }

}
