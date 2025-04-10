package com.example.allin.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice// 이건 1개만 작성해야 함

public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpservletRequest) {
        log.error("MethodArgumentNotValidException :{}", e.getMessage()); //내부 디버그용 에러 메세지
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;// errorCode에 INVALID_INPUT_VALUE 저장
        String errorMessage = e.getBindingResult().getFieldErrors() != null ?
                e.getBindingResult().getFieldError().getDefaultMessage() : errorCode.getMessage();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())
                .error(errorCode.getError())
                .code(errorCode.getCode())
                .message(errorMessage)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));





    }
}