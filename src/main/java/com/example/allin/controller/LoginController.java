package com.example.allin.controller;

import com.example.allin.entity.User;
import com.example.allin.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    // 로그인
    @PostMapping
    public User login (@RequestBody User user, Long id) {
        User loggedInUser = loginService.login(user, id);

        return loggedInUser;
    }



}
