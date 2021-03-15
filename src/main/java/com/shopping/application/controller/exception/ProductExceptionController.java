package com.shopping.application.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shopping.application.exception.CategoryCreationException;
import com.shopping.application.exception.ErrorMessage;
import com.shopping.application.exception.NoCategoryException;
import com.shopping.application.exception.ProductNotFoundException;
import com.shopping.application.exception.UuidConversionException;

@RestControllerAdvice
public class ProductExceptionController  {
    private static final Logger logger = LoggerFactory.getLogger(ProductExceptionController.class);


    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<ErrorMessage> numberFormatException(NumberFormatException e){
        logger.error("cannot format number");
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(value = NoCategoryException.class)
    public ResponseEntity<ErrorMessage> noCategoryException(NoCategoryException e){
        logger.error("no category is provided");
        ErrorMessage errorMessage = ErrorMessage.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(e.getMessage()).build();
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UuidConversionException.class)
    public ResponseEntity<ErrorMessage> stringToUuidConversion(UuidConversionException e){
        logger.error("String to UUID conversion failed");
        ErrorMessage errorMessage = ErrorMessage.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(e.getEXCEPTION_MESSAGE()).build();
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> productNotFoundException(ProductNotFoundException e){
        logger.error("Product no found");
        ErrorMessage errorMessage = ErrorMessage.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(e.getPRODUCT_NOT_FOUND()).build();
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = CategoryCreationException.class)
    public ResponseEntity<ErrorMessage> productCategoryCreationException(CategoryCreationException e){
        logger.error("cannot create category");
        ErrorMessage errorMessage = ErrorMessage.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(e.getPRODUCT_CATEGORY_CANNOT_BE_CREATED()).build();
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }
    }
