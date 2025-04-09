package com.example.allin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_VALUE( 400, "BAD_REQUEST", "U001", "Invalid Input Value"),

    // 친구 관련 에러 코드
    FRIEND_NOT_FOUND(404, "NOT_FOUND", "F001", "친구를 찾을 수 없습니다."),
    FRIEND_REQUEST_SENT(400, "BAD_REQUEST", "F002", "이미 친구 요청을 보냈습니다."),
    INVALID_FRIEND(400, "BAD_REQUEST", "F003", "잘못된 친구 요청입니다."),
    UNAUTHORIZED(401, "UNAUTHORIZED", "F004", "로그인이 필요합니다.");

    private final int status; //에러 상태 번호
    private final String error; // 에러 원인
    private final String code; // 개발자가 확인하기 편하게 할 에러 코드
    private final String message;  // 사용자에게 보여줄 에러 메세지
}