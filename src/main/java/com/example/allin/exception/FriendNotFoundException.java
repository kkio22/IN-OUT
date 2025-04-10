package com.example.allin.exception;

import lombok.Getter;

@Getter
public class FriendNotFoundException extends CustomException {
    public FriendNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}


