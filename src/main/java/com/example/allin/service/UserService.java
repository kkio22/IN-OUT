package com.example.allin.service;

import com.example.allin.config.PasswordEncoder;
import com.example.allin.dto.SessionResponseDto;
import com.example.allin.dto.UserResponseDto;
import com.example.allin.entity.User;
import com.example.allin.exception.*;
import com.example.allin.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto save(String username, String email, String password) {
        User findEmail = userRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST));//이거 Repository 개념 다시 설명 받아야 할 듯
        if(findEmail.getEmail().equals(email)){
            throw new DuplicateEmailException(ErrorCode.DUPLICATE_EMAIL);
        }
        User user = new User(username, email, passwordEncoder.encode(password)); //데이터 타입을 DTO -> Entity로 변경

        User saveUser = userRepository.save(user);// user entity를 데이터 베이스에 저장

        return new UserResponseDto(saveUser.getId(), saveUser.getUser());
    }


    @Transactional
    public UserResponseDto findById(Long userId) {
        User findId = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        return new UserResponseDto(findId.getId(), findId.getUsername());
    }

    @Transactional
    public void findByPassword(Long userId, String oldPassword, String newPassword) {
        User findPassword = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        if (!passwordEncoder.matches(oldPassword, findPassword.getPassword())) {
            throw new PasswordMismatchException(ErrorCode.INVALID_INPUT_PASSWORD);

        }
        if (passwordEncoder.matches(newPassword, findPassword.getPassword())) {
            throw new InvalidPasswordException(ErrorCode.DUPLICATE_PASSWORD);
        }


        findPassword.editPassword(passwordEncoder.encode(newPassword));

    }

    @Transactional
    public void deletePassword(Long userId, String password, SessionResponseDto sessionResponseDto) {
        User findPassword = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        if (!findPassword.getEmail().equals(sessionResponseDto.getEmail())) {
            throw new UserIdMismatchException(ErrorCode.INVALID_INPUT_ID);
        }
        if (!passwordEncoder.matches(password, findPassword.getPassword())) {
            throw new PasswordMismatchException(ErrorCode.INVALID_INPUT_PASSWORD);
        }

        //그 삭제 요청으로 들어오면 해당 시간 값을 넣어주는 매서드를 만들어서 값을 넣어주면 됨
        findPassword.softDelete();
    }
}
