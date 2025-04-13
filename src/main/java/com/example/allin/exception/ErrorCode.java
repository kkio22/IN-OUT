package com.example.allin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // login
    LOGIN_NON_AUTHORITATIVE_INFORMATION (401, "unAuthorized", "L001", "로그인 먼저 해주세요"),

    INVALID_INPUT_PASSWORD(400, "BAD_REQUEST", "U001","비밀번호가 다릅니다."),
    INVALID_INPUT_ID(400, "BAD_REQUEST", "U002", "아이디가 다릅니다." ),
    DUPLICATE_PASSWORD(400, "BAD_REQUEST", "U003", "이전 비밀번호와 같습니다."),
    INVALID_INPUT_VALUE( 400, "BAD_REQUEST", "U004", "잘못된 비밀번호입니다."),

    // 친구 관련 에러 코드
    FRIEND_NOT_FOUND(404, "NOT_FOUND", "F001", "친구를 찾을 수 없습니다."),
    FRIEND_REQUEST_SENT(400, "BAD_REQUEST", "F002", "이미 친구 요청을 보냈습니다."),
    INVALID_FRIEND(400, "BAD_REQUEST", "F003", "잘못된 친구 요청입니다."),
    UNAUTHORIZED(403, "UNAUTHORIZED", "F004", "로그인이 필요합니다."),

    // Post
    POST_NOT_FOUND(401, "BAD_REQUEST", "P001", "게시물이 존재하지 않습니다."),
    USER_NOT_FOUND(401, "BAD_REQUEST", "P002", "회원이 존재하지 않습니다."),

    //comment
    COMMENT_USER_NOT_FOUND(400, "BAD_REQUEST", "C001", "사용자가 없습니다."),
    COMMENT_LIKE_NOT_FOUND(400, "BAD_REQUEST", "C002", "좋아요 기록이 없습니다."),
    COMMENT_NOT_FOUND(400, "BAD_REQUEST", "C003", "댓글이 없습니다.");

    private final int status; //에러 상태 번호
    private final String error; // 에러 원인
    private final String code; // 개발자가 확인하기 편하게 할 에러 코드
    private final String message;  // 사용자에게 보여줄 에러 메세지
}