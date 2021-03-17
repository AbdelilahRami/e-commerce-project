package com.shopping.application.controller.exception;

import com.shopping.application.exception.ErrorMessage;
import com.shopping.application.exception.OrderNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionController {

    private static final Logger logger = LoggerFactory.getLogger(OrderExceptionController.class);

    @ExceptionHandler(value = OrderNotFoundException.class)
    public ResponseEntity<ErrorMessage> numberFormatException(OrderNotFoundException e){
        logger.error("cannot find order");
        ErrorMessage errorMessage = ErrorMessage.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(e.getMessage()).build();
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }
}
