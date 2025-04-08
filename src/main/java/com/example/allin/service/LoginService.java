package com.example.allin.service;

import com.example.allin.config.PasswordEncoder;
import com.example.allin.dto.LoginRequestDto;
import com.example.allin.entity.User;
import com.example.allin.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    // 로그인
    @Transactional
    public User login (LoginRequestDto loginRequestDto, Long id) {
        User existingUser = userRepository.findById().orElseThrow(() -> new RuntimeException("가입되어 있는 유저가 아닙니다."));

        return existingUser;
    }



}
