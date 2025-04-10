package com.example.allin.exception;

import lombok.Getter;

@Getter

public class InvalidPasswordException extends CustomException {


    public InvalidPasswordException(ErrorCode errorCode) {
        super(errorCode);
    }
}

