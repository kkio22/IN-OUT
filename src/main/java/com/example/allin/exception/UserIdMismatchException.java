package com.example.allin.exception;

import lombok.Getter;

@Getter
public class UserIdMismatchException extends CustomException{


    public UserIdMismatchException(ErrorCode errorCode) {
        super(errorCode);
    }
}
