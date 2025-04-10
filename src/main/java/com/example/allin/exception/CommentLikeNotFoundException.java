package com.example.allin.exception;

public class CommentLikeNotFoundException extends CustomException {

    public CommentLikeNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
