package com.example.allin.service;

import com.example.allin.config.PasswordEncoder;
import com.example.allin.dto.LoginRequestDto;
import com.example.allin.dto.LoginResponseDto;
import com.example.allin.entity.User;
import com.example.allin.exception.ErrorCode;
import com.example.allin.exception.PasswordMismatchException;
import com.example.allin.exception.NotExistingUserException;
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
    public LoginResponseDto login (LoginRequestDto loginRequestDto) {

        User existingUser = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(()
                -> new NotExistingUserException(ErrorCode.USER_NOT_FOUND));

        if(existingUser.isDeleted()){
            throw new NotExistingUserException(ErrorCode.USER_NOT_FOUND);

        }

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), existingUser.getPassword())) {
            throw new PasswordMismatchException(ErrorCode.INVALID_INPUT_PASSWORD);
        }

        return new LoginResponseDto(existingUser.getId(), existingUser.getUsername(), "로그인 성공했습니다.");

    }

}
