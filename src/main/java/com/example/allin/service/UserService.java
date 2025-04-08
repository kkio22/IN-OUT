package com.example.allin.service;

import com.example.allin.config.PasswordEncoder;
import com.example.allin.dto.UserResponseDto;
import com.example.allin.entity.User;
import com.example.allin.repository.UserRepository;
import jakarta.transaction.Transactional;
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

        return new UserResponseDto(saveUser.getUser());
    }

    @Transactional
    public UserResponseDto findById(Long userId) {
        User findId = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        return new UserResponseDto (findId.getUser());
    }
}
