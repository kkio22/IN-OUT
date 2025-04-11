package com.example.allin.exception;

public class UserPostException extends CustomException {
    public UserPostException(ErrorCode errorCode) {
        super(errorCode);
    }
}
