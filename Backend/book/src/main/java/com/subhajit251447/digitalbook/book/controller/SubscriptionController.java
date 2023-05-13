package com.subhajit251447.digitalbook.book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subhajit251447.digitalbook.book.dto.SubscriptionDto;
import com.subhajit251447.digitalbook.book.model.Book;
import com.subhajit251447.digitalbook.book.model.Subscription;
import com.subhajit251447.digitalbook.book.service.SubscriptionService;

@RestController
@RequestMapping(path = "/api/v1/digitalbooks")
@CrossOrigin(origins = "http://localhost:8081")
public class SubscriptionController {
    
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping(path = "/subscribe")
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
        return ResponseEntity.ok(subscriptionService.createSubscription(subscription));
    }

    @GetMapping(path = "/reader/{userId}/books")
    public ResponseEntity<List<SubscriptionDto>> getSubscription(@PathVariable Integer userId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptions(userId));
    }

    @GetMapping(path = "/reader/{userId}/books/{subscriptionId}")
    public ResponseEntity<Book> getSubscribedBook(@PathVariable Integer userId, @PathVariable Integer subscriptionId) {
        return ResponseEntity.ok(subscriptionService.getSubscribedBook(userId, subscriptionId));
    }

    @GetMapping(path = "/reader/{userId}/books/{subscriptionId}/read")
    public ResponseEntity<Map<String, String>> readBook(@PathVariable Integer userId, @PathVariable Integer subscriptionId) {
        Map<String, String> response = new HashMap<>();
        response.put("content", subscriptionService.getSubscribedBookContent(userId, subscriptionId));
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/reader/{userId}/books/{subscriptionId}/cancel-subscription")
    public ResponseEntity<Object> deleteSubscription(@PathVariable Integer userId, @PathVariable Integer subscriptionId) {
        subscriptionService.deleteSubscription(userId, subscriptionId);
        return ResponseEntity.ok(null);
    }
}
