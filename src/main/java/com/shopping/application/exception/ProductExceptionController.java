package com.shopping.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<ErrorMessage> numberFormatException(NumberFormatException e){

        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(value = NoCategoryException.class)
    public ResponseEntity<ErrorMessage> noCategoryException(NoCategoryException e){
        ErrorMessage errorMessage = ErrorMessage.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(e.getMessage()).build();
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UuidConversionException.class)
    public ResponseEntity<ErrorMessage> stringToUuidConversion(UuidConversionException e){
        ErrorMessage errorMessage = ErrorMessage.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(e.getEXCEPTION_MESSAGE()).build();
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> productNotFoundException(ProductNotFoundException e){
        ErrorMessage errorMessage = ErrorMessage.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(e.getPRODUCT_NOT_FOUND()).build();
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

}
