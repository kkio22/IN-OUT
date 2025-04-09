package com.example.allin.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UserRequestDto {

    @NotEmpty(message = "사용자 이름을 입력해주세요.")
    @Pattern(
            regexp = "^[a-z]+$",
            message = "username은 소문자 영어만 입력 가능합니다.")
    private String username;

    @NotEmpty(message = "이메일을 입력해주세요")
    @Email(message = "올바른 이메일 주소를 입력해주세요")
    @Size(min = 10, max = 25, message = "이메일 길이는 10~25자여야 합니다")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{10,}$",
            message = "비밀번호는 최소 10자 이상이며, 영문, 숫자, 특수문자를 포함해야 합니다."
    )
    private String password;
}
