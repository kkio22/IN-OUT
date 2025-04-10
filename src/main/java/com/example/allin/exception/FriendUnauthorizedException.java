package com.example.allin.exception;

import lombok.Getter;

@Getter
public class FriendUnauthorizedException extends CustomException {


    public FriendUnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}


