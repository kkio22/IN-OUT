package com.example.allin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
//
@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_VALUE( 400, "BAD_REQUEST", "U001", "Invalid Input Value");


    private final int status; //에러 상태 번호
    private final String error; // 에러 원인
    private final String code; // 개발자가 확인하기 편하게 할 에러 코드
    private final String message;  // 사용자에게 보여줄 에러 메세지
}
