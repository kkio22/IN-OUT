package com.example.allin.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice

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
                .path(httpservletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));

    }

    @ExceptionHandler(FriendNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerFriendNotFoundException(FriendNotFoundException e, HttpServletRequest httpServletRequest) {
        log.error("FriendNotFoundException : {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())
                .error(errorCode.getError())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlerPasswordMismatchException(PasswordMismatchException e, HttpServletRequest httpServletRequest) {
        log.error("PasswordMismatchException :{}", e.getMessage());//디버그용 에러 메세지, e에 들어간 건 지금은 errorcode에 있는 거지만, 원래는 ()안에 글을 넣으면 e에 넣는 문구랑, errorcode에 넣는 문구랑 달라서 원하는 것 가져올 수 있음, PasswordMismatchException 클래스에 생성자에 super로 값 넣음
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())// 사용자가 볼 수 있게 JSON 형태로 보내는 BODY에 들어가는 것
                .error(errorCode.getError())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));//이건 HTTP 헤더에 들어가는 상태코드 HTTP 응답 자체의 상태 코드여서 브라우저나 POSTMAN이 보는 것
    }

    @ExceptionHandler(UserIdMismatchException.class)
    public ResponseEntity<ErrorResponse> handlerUserIdMismatchException(UserIdMismatchException e, HttpServletRequest httpServletRequest) {
        log.error("UserIdMismatchException : {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())
                .error(errorCode.getError())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));

    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handlerInvalidPasswordException(InvalidPasswordException e, HttpServletRequest httpServletRequest) {
        log.error("InvalidPasswordException : {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())
                .error(errorCode.getError())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));

    }

    @ExceptionHandler(FriendRequestSentException.class)
    public ResponseEntity<ErrorResponse> handleFriendRequestSentException(FriendRequestSentException e, HttpServletRequest httpServletRequest) {
        log.error("FriendRequestSentException : {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())
                .error(errorCode.getError())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(FriendInvalidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFriendException(FriendInvalidException e, HttpServletRequest httpServletRequest) {
        log.error("InvalidFriendException : {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())
                .error(errorCode.getError())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(FriendUnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedFriendException(FriendUnauthorizedException e, HttpServletRequest httpServletRequest) {
        log.error("FriendUnauthorizedException : {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())
                .error(errorCode.getError())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
    }


}
