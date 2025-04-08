package com.example.allin.entity;

import com.example.allin.entity.Post;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    //유저
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //포스트
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    //댓글내용
    @Column(nullable = false)
    private String commentContent;

    //작성, 수정 시간
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //좋아요 카운트
    @Column(nullable = false)
    private int likeCount = 0;

    //댓글 수정한 뒤 시각 갱신
    public void update(String content) {
        this.commentContent = content;
        this.updatedAt = LocalDateTime.now();
    }

    //좋아요 수 증가
    public void like() {
        this.likeCount++;
    }

    //좋아요 수 감소
    public void unlike() {
        if (this.likeCount > 0) this.likeCount--;
    }
}
