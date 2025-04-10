package com.example.allin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

//
@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_PASSWORD(400, "BAD_REQUEST", "U001","비밀번호가 다릅니다."),

    INVALID_INPUT_ID(400, "BAD_REQUEST", "U002", "아이디가 다릅니다." ),

    DUPLICATE_PASSWORD(400, "BAD_REQUEST", "U003", "이전 비밀번호와 같습니다."),

    INVALID_INPUT_VALUE( 400, "BAD_REQUEST", "U004", "Invalid Input Value");


    private final int status; //에러 상태 번호
    private final String error; // 에러 원인
    private final String code;
    private final String message;// 개발자가 확인하기 편하게 할 에러 코드
    }
