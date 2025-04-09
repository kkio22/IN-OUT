package com.example.allin.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeletePasswordRequestDto {


    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{10,}$",
            message = "비밀번호는 최소 8자 이상이며, 영문, 숫자, 특수문자를 1개 이상 포함해야 합니다."
    )
    private String password;


}
