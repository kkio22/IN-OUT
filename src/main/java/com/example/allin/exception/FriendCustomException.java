package com.example.allin.exception;

public class FriendCustomException extends RuntimeException {

    private final ErrorCode errorCode;

    public FriendCustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
