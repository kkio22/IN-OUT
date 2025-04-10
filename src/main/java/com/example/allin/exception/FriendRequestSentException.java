package com.example.allin.exception;

import lombok.Getter;

@Getter
public class FriendRequestSentException extends CustomException {


    public FriendRequestSentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
