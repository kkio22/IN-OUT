package com.example.allin.controller;

import com.example.allin.dto.LoginRequestDto;
import com.example.allin.dto.LoginResponseDto;
import com.example.allin.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
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
        LoginResponseDto login = loginService.login(loginRequestDto, httpServletRequest);

        return new ResponseEntity<>(login, HttpStatus.OK);

    }

    // 로그인 상태 확인


    // 로그아웃


}
