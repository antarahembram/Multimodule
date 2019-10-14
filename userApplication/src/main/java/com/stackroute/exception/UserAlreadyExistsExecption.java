package com.stackroute.exception;

public class UserAlreadyExistsExecption extends Exception {
    private String  message;

    public UserAlreadyExistsExecption() {
    }

    public UserAlreadyExistsExecption(String message) {
        //message will be displayed when exception occurs
        super(message);
        this.message = message;
    }
}
