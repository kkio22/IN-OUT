package com.example.allin.service;

import com.example.allin.config.PasswordEncoder;
import com.example.allin.dto.UserResponseDto;
import com.example.allin.entity.User;
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

        User user = new User(username, email, passwordEncoder.encode(password));

        User saveUser = userRepository.save(user);

        return new UserResponseDto(saveUser.getId(), saveUser.getUser());
    }

    @Transactional
    public UserResponseDto findById(Long userId) {
        User findId = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        return new UserResponseDto(findId.getId(), findId.getUser());
    }

    @Transactional
    public void findByPassword(Long userId, String oldPassword, String newPassword) {
        User findPassword = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        if (!passwordEncoder.matches(oldPassword, findPassword.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 다릅니다.");

        }
        if (passwordEncoder.matches(newPassword, findPassword.getPassword())) {
            throw new IllegalArgumentException("이전 비밀번호와 같습니다.");
        }


        findPassword.editPassword(passwordEncoder.encode(newPassword));

    }

    @Transactional
    public void deletePassword(Long userId, String password, UserResponseDto userResponseDto) {
        User findPassword = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        if (!findPassword.getEmail().equals()) {
            throw new IllegalArgumentException("아이디가 다릅니다.");
        }
        if (!passwordEncoder.matches(password, findPassword.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 다릅니다.");
        }

    }
}
