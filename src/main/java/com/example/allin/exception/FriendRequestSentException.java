package com.example.allin.exception;

import lombok.Getter;

@Getter
public class FriendRequestSentException extends RuntimeException {

    private final ErrorCode errorCode;

    public FriendRequestSentException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
