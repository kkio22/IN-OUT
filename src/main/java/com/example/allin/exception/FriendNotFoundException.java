package com.example.allin.exception;

import lombok.Getter;

@Getter
public class FriendNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public FriendNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
