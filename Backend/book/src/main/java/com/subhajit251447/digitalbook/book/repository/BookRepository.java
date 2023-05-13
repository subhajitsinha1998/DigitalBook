package com.subhajit251447.digitalbook.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.subhajit251447.digitalbook.book.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select b from Book b where " 
        +"(:category is null or b.category = :category) and "
        +"(:title is null or b.title = :title) and "
        +"(:author is null or b.author = :author) and "
        +"(:price is null or b.price = :price) and "
        +"(:publisher is null or b.publisher = :publisher)")
    public List<Book> customSearchBooks(String category, String title, String author,
        String price, String publisher);
    
}
