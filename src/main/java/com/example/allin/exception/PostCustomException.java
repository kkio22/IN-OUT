package com.example.allin.exception;

public class PostCustomException extends CustomException {

    public PostCustomException(ErrorCode errorCode) {
        super(errorCode);
    }

    public PostCustomException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
