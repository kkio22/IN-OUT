package com.example.allin.dto;

import com.example.allin.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private Long likeCount;

    public CommentResponseDto(Comment comment, Long likeCount) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUser().getUsername();
        this.createdAt = comment.getCreatedAt();
        this.likeCount = likeCount;
    }
}
