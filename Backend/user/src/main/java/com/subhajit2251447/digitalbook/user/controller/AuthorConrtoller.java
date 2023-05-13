package com.subhajit2251447.digitalbook.user.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.subhajit2251447.digitalbook.user.Exception.CustomException;
import com.subhajit2251447.digitalbook.user.Exception.NotFoundException;
import com.subhajit2251447.digitalbook.user.service.UserService;

@RestController
@RequestMapping(path = "/api/v1/digitalbooks/author")
@CrossOrigin
public class AuthorConrtoller {

    @Autowired
    private UserService userService;

    private String bookServiceBaseUrl = "http://localhost:8081/api/v1/digitalbooks";

    @PostMapping(path = "/{authorId}/book")
    public ResponseEntity<Object> createBook(@RequestBody Object book, @PathVariable Integer authorId) {
        userService.userExists(authorId);
        String url = bookServiceBaseUrl + "/create";
        ResponseEntity<Object> responseEntity = 
            new RestTemplate().postForEntity(url, book, Object.class);
        return responseEntity;
    }

    @GetMapping(path = "/{authorId}/books")
    public ResponseEntity<Object> search(@PathVariable Integer authorId) {
        String author = userService.getUser(authorId).getFullName();
        String url = bookServiceBaseUrl + "/author/"+author+"/books";
        ResponseEntity<Object> responseEntity = 
            new RestTemplate().getForEntity(url, Object.class);
        return responseEntity;
    }

    @PostMapping(path = "/{authorId}/books/{bookId}")
    public ResponseEntity<Object> updateBook(@RequestBody(required = false) Object book, @PathVariable Integer authorId, 
        @PathVariable Integer bookId, @RequestParam(required = false) Boolean block) throws CustomException {
            userService.userExists(authorId);
            String url = bookServiceBaseUrl + "/author/{authorId}/books/{bookId}";
            Map<String, Integer> params = new HashMap<>();
            params.put("authorId", authorId);
            params.put("bookId", bookId);
            ResponseEntity<Object> responseEntity;
            try {
                if (block == null) {
                    responseEntity = new RestTemplate().postForEntity(url, book, 
                        Object.class, params);
                } else {
                    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url);
                    uriBuilder.queryParam("block", (Boolean)block);
                    responseEntity = new RestTemplate().postForEntity(
                        uriBuilder.buildAndExpand(params).toUri(), 
                        book, Object.class);
                }
            } catch (HttpClientErrorException e) {
                if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) throw new NotFoundException(e.getResponseBodyAsString()); 
                else throw new CustomException(e.getMessage());
            }
             
            return responseEntity;
    }
    
}
