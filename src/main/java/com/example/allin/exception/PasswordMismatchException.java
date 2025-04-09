package com.example.allin.exception;

import lombok.Getter;

@Getter
public class PasswordMismatchException extends RuntimeException {

  private final ErrorCode errorCode; //의존성 주입

  public PasswordMismatchException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }


}
