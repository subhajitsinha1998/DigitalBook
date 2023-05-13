package com.subhajit2251447.digitalbook.user.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}