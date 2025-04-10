package com.example.allin.exception;

import lombok.Getter;

@Getter
public class FriendInvalidException extends CustomException {


    public FriendInvalidException(ErrorCode errorCode) {
        super(errorCode);
    }
}
