package com.shopping.application.exception;

import lombok.Getter;

@Getter
public class UuidConversionException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String EXCEPTION_MESSAGE = "cannot convert string to uuid";

    public UuidConversionException(String message) {
        super(message);
    }

    public UuidConversionException() {
    }
}
