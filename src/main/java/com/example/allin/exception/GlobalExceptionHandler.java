package com.example.allin.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * @Slf4j : log.info(), log.error() 등을 쓸 수 있게 해주는 어노테이션
 * @RestControllerAdvice : @ControllerAdvice + @ResponseBody 합친 버전
 */
@Slf4j
@RestControllerAdvice
// 이 클래스는 협업에서 한 개만 작성해야 한다
public class GlobalExceptionHandler {

    // LoginCustomException 이라는 커스텀 예외가 발생했을 때 이 메서드가 실행된다
    @ExceptionHandler(LoginCustomException.class)
    // ResponseEntity를 써서 HTTP 상태 코드 + JSON 응답을 모두 세팅
    public ResponseEntity<ErrorResponse> handlerLoginCustomException(LoginCustomException e, HttpServletRequest httpServletRequest) {
        // 콘솔에 에러메시지 출력
        log.error("LoginCustomException :{}", e.getMessage());  //디버그용 에러 메세지
        ErrorCode errorCode = e.getErrorCode();  // 커스텀 예외에 정의된 에러코드 정보를 가져온다
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())  // HTTP 상태 코드 (예: 401, 403, 500 등)
                .error(errorCode.getError())  // 에러명 (예: UNAUTHORIZED, BAD_REQUEST 등)
                .code(errorCode.getCode())  // 커스텀 정의한 코드 (예: "L001")
                .message(errorCode.getMessage())  // 사용자에게 보여줄 메시지
                .path(httpServletRequest.getRequestURI())  // 요청 경로 (/users/login 등)
                .build();  // 마지막에 꼭 해주는 약속같은 것
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
        /*
           * 숫자로 되어 있는 상태 코드(ex. 400, 401)를
           Spring의 HttpStatus 열거형(enum)으로 변환
           → 응답의 상태 코드(status) 에 들어감
           → 이 코드는 사용자에게 JSON 형태로 에러를 설명하면서, 동시에 HTTP 응답 코드도 정확히 전달하는 역할을 한다
           * ResponseEntity.ok(body) → 상태 코드 200으로 바로 보낼 수도 있다
           * ResponseEntity.status(404).body(body) → 이렇게 체이닝도 가능
         */
    }






}