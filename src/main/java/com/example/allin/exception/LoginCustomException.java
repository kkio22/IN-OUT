package com.example.allin.exception;

public class LoginCustomException extends RuntimeException {
    public LoginCustomException(String message) {
        super(message);
    }
}
