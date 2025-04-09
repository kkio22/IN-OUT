package com.example.allin.exception;

import lombok.Getter;

@Getter
public class UserIdMismatchException extends RuntimeException{

    private final ErrorCode errorCode;

    public UserIdMismatchException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
