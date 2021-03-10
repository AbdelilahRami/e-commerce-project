package com.shopping.application.exception;

public class UserNotFound extends Exception {

    private static final long serialVersionUID = 1L;
    
    private String message = "User not found";

    @Override
    public String getMessage() {
        return message;
    }
}
