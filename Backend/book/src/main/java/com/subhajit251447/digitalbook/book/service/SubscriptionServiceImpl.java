package com.subhajit251447.digitalbook.book.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subhajit251447.digitalbook.book.dto.SubscriptionDto;
import com.subhajit251447.digitalbook.book.exception.NotFoundException;
import com.subhajit251447.digitalbook.book.model.Book;
import com.subhajit251447.digitalbook.book.model.Subscription;
import com.subhajit251447.digitalbook.book.repository.BookRepository;
import com.subhajit251447.digitalbook.book.repository.SubscriptionRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private BookRepository bookRepository;

    private void activeBook(Subscription subscription) {
        if (!bookRepository.existsById(subscription.getBookId()) || 
            !bookRepository.findById(subscription.getBookId()).orElse(null).getActive())
                throw new NotFoundException("Book not found");
    }

    @Override
    public Subscription createSubscription(Subscription subscription) throws NotFoundException {
        this.activeBook(subscription);
        return subscriptionRepository.save(subscription);
    }

    @Override
    public List<SubscriptionDto> getSubscriptions(Integer userId) {
        return subscriptionRepository.findByUserId(userId)
            .stream().filter(
                subscription->bookRepository.findById(
                    subscription.getBookId()).orElse(null).getActive())
                    .map(subscription->new SubscriptionDto(subscription, bookRepository.getReferenceById(subscription.getBookId())))
                    .collect(Collectors.toList());
    }

    private Subscription getSubscriptionByIdAndUserId(Integer userId, Integer subscriptionId) {
        Subscription subscription = subscriptionRepository.findByIdAndUserId(subscriptionId, userId);
        this.activeBook(subscription);
        return subscription;
    }

    @Override
    public Book getSubscribedBook(Integer userId, Integer subscriptionId) {
        Subscription subscription = getSubscriptionByIdAndUserId(userId, subscriptionId);
        this.activeBook(subscription);
        return bookRepository.findById(subscription.getBookId()).orElse(null);
    }

    @Override
    public String getSubscribedBookContent(Integer userId, Integer subscriptionId) {
        return getSubscribedBook(userId, subscriptionId).getContent();
    }

    @Override
    public void deleteSubscription(Integer userId, Integer subscriptionId) {
        Subscription subscription = getSubscriptionByIdAndUserId(userId, subscriptionId);
        subscriptionRepository.delete(subscription);
    }
    
}
