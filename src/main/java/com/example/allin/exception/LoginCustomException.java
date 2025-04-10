package com.example.allin.exception;

import lombok.Getter;

@Getter
public class LoginCustomException extends CustomException {

    public LoginCustomException(ErrorCode errorCode) {
        super(errorCode);
    }
}
