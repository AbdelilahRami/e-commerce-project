package com.shopping.application.controller.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shopping.application.exception.UserNotFoundException;

@RestControllerAdvice
public class UserControllerException {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleMethodUserNotFoundException(UserNotFoundException e) {

        return ResponseEntity.notFound().build();

    }
}
