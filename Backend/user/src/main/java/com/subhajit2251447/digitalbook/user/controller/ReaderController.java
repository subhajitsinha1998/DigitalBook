package com.subhajit2251447.digitalbook.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.subhajit2251447.digitalbook.user.Exception.CustomException;
import com.subhajit2251447.digitalbook.user.Exception.NotFoundException;
import com.subhajit2251447.digitalbook.user.service.UserService;

@RestController
@RequestMapping(path = "/api/v1/digitalbooks/reader")
@CrossOrigin
public class ReaderController {

    @Autowired
    private UserService userService;

    private String bookServiceBaseUrl = "http://localhost:8081/api/v1/digitalbooks";

    @PostMapping(path = "/book/{bookId}/subscribe")
    public ResponseEntity<Object> createSubscription(@RequestBody Map<String, Integer> subscription) throws CustomException {
        userService.userExists(subscription.get("userId"));
        String url = bookServiceBaseUrl + "/subscribe";
        ResponseEntity<Object> response;
        try {
            response = new RestTemplate().postForEntity(url, subscription, Object.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) throw new NotFoundException(e.getResponseBodyAsString()); 
            else throw new CustomException(e.getMessage());
        }
        return response;
    }

    @GetMapping(path = "/{userId}/books")
    public ResponseEntity<Object> getSubscription(@PathVariable Integer userId) throws CustomException {
        userService.userExists(userId);
        String url = bookServiceBaseUrl + "/reader/{userId}/books";
        ResponseEntity<Object> response;
        try {
            response = new RestTemplate().getForEntity(url, Object.class, userId);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) throw new NotFoundException(e.getResponseBodyAsString()); 
            else throw new CustomException(e.getMessage());
        }
        return response;
    }

    @GetMapping(path = "/{userId}/books/{subscriptionId}")
    public ResponseEntity<Object> getSubscribedBook(@PathVariable Integer userId, @PathVariable Integer subscriptionId) throws CustomException {
        userService.userExists(userId);
        String url = bookServiceBaseUrl + "/reader/{userId}/books/{subscriptionId}";
        ResponseEntity<Object> response;
        try {
            response = new RestTemplate().getForEntity(url, 
                Object.class, userId, subscriptionId);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) throw new NotFoundException(e.getResponseBodyAsString()); 
            else throw new CustomException(e.getMessage());
        }
        return response;
    }

    @GetMapping(path = "/{userId}/books/{subscriptionId}/read")
    public ResponseEntity<Object> readBook(@PathVariable Integer userId, @PathVariable Integer subscriptionId) throws CustomException {
        userService.userExists(userId);
        String url = bookServiceBaseUrl + "/reader/{userId}/books/{subscriptionId}/read";
        ResponseEntity<Object> response;
        try {
            response = new RestTemplate().getForEntity(url, 
                Object.class, userId, subscriptionId);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) throw new NotFoundException(e.getResponseBodyAsString()); 
            else throw new CustomException(e.getMessage());
        }
        return response;
    }

    @PostMapping(path = "/{userId}/books/{subscriptionId}/cancel-subscription")
    public ResponseEntity<Object> deleteSubscription(@PathVariable Integer userId, @PathVariable Integer subscriptionId) throws CustomException {
        userService.userExists(userId);
        String url = bookServiceBaseUrl + "/reader/{userId}/books/{subscriptionId}/cancel-subscription";
        ResponseEntity<Object> response;
        try {
            response = new RestTemplate().postForEntity(url, null, Object.class, userId, subscriptionId);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) throw new NotFoundException(e.getResponseBodyAsString()); 
            else throw new CustomException(e.getMessage());
        }
        return response;
    }
}
