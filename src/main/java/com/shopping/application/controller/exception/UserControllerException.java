package com.shopping.application.controller.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shopping.application.exception.UserNotFound;

@RestControllerAdvice
public class UserControllerException {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Object> handleMethodUserNotFoundException(UserNotFound e) {

        return ResponseEntity.notFound().build();

    }
}
