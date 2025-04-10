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
    private Long likeCount;
    private boolean isLiked; // 좋아요 여부

    // User 테이블에 username이 user로 지정되어 있음
    public PostResponseDto(Post post) {
        this.userName = post.getUser().getUsername();
        this.title = post.getTitle();
        this.postContent = post.getPostContent();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.likeCount = post.getLikeCount();
        this.isLiked = isLiked;

    }



}
