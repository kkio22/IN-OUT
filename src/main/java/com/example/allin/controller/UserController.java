package com.example.allin.controller;

import com.example.allin.dto.*;
import com.example.allin.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<String> updatePassword(
            @PathVariable Long userId,
            @Valid @RequestBody PasswordRequestDto requestDto
    ) {
        userService.findByPassword(
                userId,
                requestDto.getOldPassword(),
                requestDto.getNewPassword()
        );
        return ResponseEntity.ok("비밀번호를 변경했습니다.");
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<String> deletePassword(
            @PathVariable Long userId,
            @Valid @RequestBody DeletePasswordRequestDto requestDto,
            HttpServletRequest httpServletRequest

    ) {
        HttpSession session = httpServletRequest.getSession();//이미 로그인 한번 한 후에 하니까 session이 있는 상황
        SessionResponseDto sessionResponseDto =  (SessionResponseDto)session.getAttribute("sessionResponseDto");//sessionId값 = 이게 USER객체 정보 담고 있음

        userService.deletePassword(userId, requestDto.getPassword(),sessionResponseDto);

        return ResponseEntity.ok("회원을 삭제했습니다.");
    }
}
