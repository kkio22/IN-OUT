package com.example.allin.controller;

import com.example.allin.dto.UserRequestDto;
import com.example.allin.dto.UserResponseDto;
import com.example.allin.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody UserRequestDto requestDto) {
        UserResponseDto userResponseDto = userService.save(
                requestDto.getUsername(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );
        return new ResponseEntity<> (userResponseDto, HttpStatus.CREATED);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findById (@PathVariable Long userId){
        return new ResponseEntity<> (userService.findById(userId), HttpStatus.OK);
    }
}
