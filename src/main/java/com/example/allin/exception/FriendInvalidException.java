package com.example.allin.exception;

import lombok.Getter;

@Getter
public class FriendInvalidException extends RuntimeException {

    private final ErrorCode errorCode;

    public FriendInvalidException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
