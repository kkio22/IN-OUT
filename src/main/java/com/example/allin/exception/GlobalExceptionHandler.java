package com.example.allin.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
// 이 클래스는 협업에서 한 개만 작성해야 한다
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginCustomException.class)
    public ResponseEntity<ErrorResponse> handlerLoginCustomException(LoginCustomException e, HttpServletRequest httpServletRequest) {
        log.error("LoginCustomException :{}", e.getMessage());  //디버그용 에러 메세지
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())  // 사용자가 볼 수 있게 JSON 형태로 보내는 BODY에 들어가는 것
                .error(errorCode.getError())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));  //이건 HTTP 헤더에 들어가는 상태코드 HTTP 응답 자체의 상태 코드여서 브라우저나 POSTMAN이 보는 것
    }






}