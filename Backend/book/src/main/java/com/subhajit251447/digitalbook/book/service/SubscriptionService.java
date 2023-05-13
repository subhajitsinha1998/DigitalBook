package com.subhajit251447.digitalbook.book.service;

import java.util.List;

import com.subhajit251447.digitalbook.book.dto.SubscriptionDto;
import com.subhajit251447.digitalbook.book.exception.NotFoundException;
import com.subhajit251447.digitalbook.book.model.Book;
import com.subhajit251447.digitalbook.book.model.Subscription;

public interface SubscriptionService {
    
    public Subscription createSubscription(Subscription subscription) throws NotFoundException;
    public List<SubscriptionDto> getSubscriptions(Integer userId);
    public Book getSubscribedBook(Integer userId, Integer subscriptionId);
    public String getSubscribedBookContent(Integer userId, Integer subscriptionId);
    public void deleteSubscription(Integer userId, Integer subscriptionId);

}
