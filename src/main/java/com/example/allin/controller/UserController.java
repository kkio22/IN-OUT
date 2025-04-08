package com.example.allin.controller;

import com.example.allin.dto.UserRequestDto;
import com.example.allin.dto.UserResponseDto;
import com.example.allin.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

}
