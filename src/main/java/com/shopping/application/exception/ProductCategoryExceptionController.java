package com.shopping.application.exception;

import com.shopping.application.dto.ProductCategoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductCategoryExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<ProductCategoryDto> numberFormatException(NumberFormatException e){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<?> nullPointerException(NullPointerException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
