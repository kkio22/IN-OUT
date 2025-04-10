package com.example.allin.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.ErrorResponse;
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
        log.error("MethodArgumentNotValidException :{}", e.getMessage());
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
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
        log.error("PasswordMismatchException :{}", e.getMessage());
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

    @ExceptionHandler(LoginCustomException.class)
    public ResponseEntity<ErrorResponse> handlerLoginCustomException(LoginCustomException e, HttpServletRequest httpServletRequest) {
        log.error("LoginCustomException :{}", e.getMessage());
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

    @ExceptionHandler(PostCustomException.class)
    public ResponseEntity<ErrorResponse> handlerPostCustomException(PostCustomException e, HttpServletRequest httpServletRequest) {
        log.error("PostCustomException : {}", e.getMessage());
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