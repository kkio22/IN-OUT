package com.example.allin.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
//이건 1개만 만들어야 함 (혐업할 때)
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String error; //에러 종류

    private String message; // 에러 메세지

    private int status; // 에러 상태

    private String code; //에러 코드

    private final LocalDateTime timestamp = LocalDateTime.now(); //에러 발생 시간
}