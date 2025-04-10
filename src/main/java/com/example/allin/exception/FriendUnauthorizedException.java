package com.example.allin.exception;

import lombok.Getter;

@Getter
public class FriendUnauthorizedException extends RuntimeException {

    private final ErrorCode errorCode;

    public FriendUnauthorizedException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
