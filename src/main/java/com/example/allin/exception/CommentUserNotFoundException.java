package com.example.allin.exception;

public class CommentUserNotFoundException extends CustomException {

    public CommentUserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
