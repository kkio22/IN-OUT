package com.example.allin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // login
    LOGIN_NON_AUTHORITATIVE_INFORMATION (401, "unAuthorized", "L001", "로그인 먼저 해주세요");


    private final int status;  // 에러 상태 번호
    private final String error;  // 에러 원인
    private final String code;  // 에러 코드
    private final String message;  // 개발자의 에러 확인 편리성을 위한 에러 메시지

}
