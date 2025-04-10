package com.example.allin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //모든 필드를 가진 생성자 자동 주입
public class PostRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private final String title;

    @NotBlank(message = "콘텐츠 내용을 입력해주세요.")
    private final String postContent;

}
