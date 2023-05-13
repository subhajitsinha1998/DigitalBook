package com.subhajit251447.digitalbook.book.dto;

import com.subhajit251447.digitalbook.book.model.Book;
import com.subhajit251447.digitalbook.book.model.Subscription;

public class SubscriptionDto {

    private Integer id;
    private Integer userId;
    private Book book;
    
    public SubscriptionDto(Subscription subscription, Book book) {
        this.id = subscription.getId();
        this.userId = subscription.getUserId();
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
}
