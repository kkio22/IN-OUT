package com.example.allin.service;

import com.example.allin.config.PasswordEncoder;
import com.example.allin.dto.UserResponseDto;
import com.example.allin.entity.User;
import com.example.allin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserResponseDto save(String username , String email, String password) {
        User user = new User(username, email, passwordEncoder.encode(password));
        User saveUser = userRepository.save(user);


    }
}
