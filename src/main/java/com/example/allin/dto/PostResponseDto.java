package com.example.allin.dto;

import com.example.allin.entity.Post;
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
    private LocalDateTime updatedAt;

    // User 테이블에 userName 컬럼이 있어야 함.
    public PostResponseDto(Post post) {
        this.userName = post.getUser().getUserName();
        this.title = post.getTitle();
        this.postContent = post.getPostContent();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();

    }



}
