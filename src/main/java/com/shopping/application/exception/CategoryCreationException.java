package com.shopping.application.exception;

import lombok.Getter;

@Getter
public class CategoryCreationException extends RuntimeException {
    private final String PRODUCT_CATEGORY_CANNOT_BE_CREATED= "PRODUCT_NOT_FOUND";
    public CategoryCreationException() {
    }

    public CategoryCreationException(String message) {
        super(message);
    }

    public CategoryCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
