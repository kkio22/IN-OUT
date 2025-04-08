package com.example.allin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "공백 없이 이메일을 입력해주세요.")
    private final String email;

    @NotBlank(message = "공백 없이 비밀번호를 입력해주세요.")
    private final String password;
}