package com.shopping.application.exception;

public class NoCategoryException extends RuntimeException {

    public NoCategoryException() {
    }

    public NoCategoryException(String message) {
        super(message);
    }
}
