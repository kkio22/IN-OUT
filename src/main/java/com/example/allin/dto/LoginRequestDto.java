package com.example.allin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "공백 없이 이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private final String email;

    @NotBlank(message = "공백 없이 비밀번호를 입력해주세요.")
    /*
     * (?=.*[A-Za-z])  → 영어 대소문자 중 하나 이상
     * (?=.*\\d)  → 숫자(0~9) 하나 이상
     * (?=.*[@$!%*#?&])  → 지정된 특수문자 중 하나 이상 포함
     * [A-Za-z\\d@$!%*#?&]{10,}  → 사용 가능한 문자만 포함되어야 하며, 10자 이상
     */
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "비밀번호는 최소 8자 이상이며, 대소문자를 포함한 영문, 숫자, 특수문자를 최소 1글자씩 포함해야 합니다.")
    @Size(min = 8, message = "비밀번호는 최소 8글자 이상이어야 합니다.")
    private final String password;

}