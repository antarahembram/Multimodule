package com.stackroute.exception;

public class UserNotFoundException extends Exception {
    private String  message;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        //message will be displayed when exception occurs
        super(message);
        this.message = message;
    }
}
