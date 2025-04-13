package com.example.allin.exception;

public class NotExistingUserException extends CustomException {
    public NotExistingUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
