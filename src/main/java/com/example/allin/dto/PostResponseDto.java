package com.example.allin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor // 생성자 자동 생성
public class PostResponseDto {
    // postId를 제외한 컬럼 출력
    private String userName;
    private String title;
    private String postContent;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


}
