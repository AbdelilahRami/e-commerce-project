package com.shopping.application.exception;

public class NoCategoryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoCategoryException() {
    }

    public NoCategoryException(String message) {
        super(message);
    }
}
