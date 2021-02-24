package com.shopping.application.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {
    private final String PRODUCT_NOT_FOUND= "PRODUCT_NOT_FOUND";

    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
