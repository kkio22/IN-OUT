package com.example.allin.service;

import com.example.allin.config.PasswordEncoder;
import com.example.allin.dto.LoginRequestDto;
import com.example.allin.dto.LoginResponseDto;
import com.example.allin.entity.User;
import com.example.allin.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 로그인
    @Transactional
    public LoginResponseDto login (LoginRequestDto loginRequestDto) {

        User existingUser = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "가입되지 않은 이메일입니다."));

        if(!existingUser.isDeleted()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "탈퇴한 회원입니다");

        }

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), existingUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        return new LoginResponseDto(existingUser.getId(), existingUser.getUsername(), "로그인 성공했습니다.");

    }

}
