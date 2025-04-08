package com.example.allin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //모든 필드를 가진 생성자 자동 주입
public class PostRequestDto {
    private final String title;
    private final String postContent;

}
