package com.example.allin.controller;

import com.example.allin.dto.LoginRequestDto;
import com.example.allin.dto.LoginResponseDto;
import com.example.allin.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login (@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletRequest httpServletRequest) {

        LoginResponseDto loginResponseDto = loginService.login(loginRequestDto);

        // 세션 생성
        HttpSession session = httpServletRequest.getSession(true);

        /*
         * session.setAttribute(String name, Object value)
         * 사용자 정보를 세션에 저장할 때 쓰는 아주 기본적인 메서드
         */
        session.setAttribute("userId", loginResponseDto);
//        session.setMaxInactiveInterval(3600);  // 초 단위로 세션 종료 시간 지정 -> 3600초 동안 유효한 세션

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);

    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession();

        /*
        invalidate : 세션이나 캐시 등의 상태를 무효화 -> 강제로 끊어버리는 기능
        빈 세션이 아니라면 세션 종료 -> 로그인 된 상태라면 로그아웃
         */
        if(session != null) {
            session.invalidate();

        } return ResponseEntity.ok("로그아웃 성공");
    }


}
