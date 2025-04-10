package com.example.allin.exception;

public class CommentNotFoundException extends CustomException {

  public CommentNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
