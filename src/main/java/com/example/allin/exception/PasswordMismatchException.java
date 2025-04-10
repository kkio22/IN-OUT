package com.example.allin.exception;

import lombok.Getter;

@Getter
public class PasswordMismatchException extends CustomException {


  public PasswordMismatchException(ErrorCode errorCode) {
    super(errorCode);
  }
}
