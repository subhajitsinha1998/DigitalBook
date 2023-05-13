package com.subhajit2251447.digitalbook.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.subhajit2251447.digitalbook.user.Exception.CustomException;
import com.subhajit2251447.digitalbook.user.dto.JwtRequest;
import com.subhajit2251447.digitalbook.user.dto.JwtResponse;
import com.subhajit2251447.digitalbook.user.model.User;
import com.subhajit2251447.digitalbook.user.service.JwtService;
import com.subhajit2251447.digitalbook.user.service.UserService;

@RestController
@RequestMapping(path = "/api/v1/digitalbooks")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @PostMapping(path = "/sign-in")
    public ResponseEntity<JwtResponse> home(@RequestBody JwtRequest jwtRequest) {
        return ResponseEntity.ok(jwtService.createToken(jwtRequest));
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity<JwtResponse> signUp(@RequestBody User user) throws CustomException {
        ResponseEntity<JwtResponse> response;
        JwtResponse userAdded;
        userAdded = userService.createUser(user);
        response = ResponseEntity.ok(userAdded);
        return response;
    }

    @GetMapping(path = "/search")
    public ResponseEntity<Object> search(@RequestParam(required = false) String category, 
        @RequestParam(required = false) String title, @RequestParam(required = false) String author,
        @RequestParam(required = false) String price, @RequestParam(required = false) String publisher) {
            String url = "http://localhost:8081/api/v1/digitalbooks/search";
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url);
            if (category != null && !category.isBlank()) uriBuilder.queryParam("category", category);
            if (title !=null && !title.isBlank()) uriBuilder.queryParam("title", title);
            if (author != null && !author.isBlank()) uriBuilder.queryParam("author", author);
            if (price != null && !price.isBlank()) uriBuilder.queryParam("price", price);
            if (publisher != null && !publisher .isBlank()) uriBuilder.queryParam("publisher", publisher);
            ResponseEntity<Object> responseEntity = 
                new RestTemplate().getForEntity(uriBuilder.build().toUri(), Object.class);
            return responseEntity;
        }
}
