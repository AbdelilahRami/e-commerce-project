package com.shopping.application.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shopping.application.exception.ErrorMessage;
import com.shopping.application.exception.ProductCategoryNotFoundException;
import com.shopping.application.exception.ProductNotFoundException;

@RestControllerAdvice
public class ProductCategoryExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryExceptionController.class);

    
    @ExceptionHandler(ProductCategoryNotFoundException.class)
    public ResponseEntity<ErrorMessage> productNotFoundException(ProductCategoryNotFoundException e){
        logger.error("Product Category no found");
        ErrorMessage errorMessage = ErrorMessage.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
