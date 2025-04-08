package com.example.allin.service;

import com.example.allin.config.PasswordEncoder;
import com.example.allin.dto.LoginRequestDto;
import com.example.allin.dto.LoginResponseDto;
import com.example.allin.entity.User;
import com.example.allin.repository.UserRepository;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpSession;
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
    private final ServletRequest httpServletRequest;

    // 로그인
    @Transactional
    public LoginResponseDto login (LoginRequestDto loginRequestDto, Long id) {

        User existingUser = userRepository.findByEmail(email).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(existingUser.getPassword(), loginRequestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.")
        }

        // 세션 생성
        HttpSession session = httpServletRequest.getSession(true);

        /**
         * session.setAttribute(String name, Object value)
         * 사용자 정보를 세션에 저장할 때 쓰는 아주 기본적인 메서드
         */
        session.setAttribute("userId", existingUser.getId());

        return new LoginResponseDto(existingUser.getId(), existingUser.getUserName(), "로그인 성공했습니다.");

    }



}
