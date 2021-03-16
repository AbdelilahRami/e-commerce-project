package com.shopping.application.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String PRODUCT_NOT_FOUND= "PRODUCT_NOT_FOUND";

    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
