package com.example.allin.controller;

import com.example.allin.dto.SessionResponseDto;
import com.example.allin.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final PostLikeService postLikeService;

    // 게시물 좋아요/좋아요 취소
    @PutMapping("/likes/{postId}")
    public ResponseEntity<Void> postLike(
            @PathVariable Long postId,
            @SessionAttribute(name = "sessionResponseDto")SessionResponseDto sessionResponseDto
            ) {
        postLikeService.toggleLike(postId, sessionResponseDto.getId());
        return ResponseEntity.ok().build();
    }

    // 게시물별 좋아요 수 출력
    @GetMapping("/posts/{postId}/likes/count")
    public ResponseEntity<Long> countLikes(
            @PathVariable Long postId
    ) {
        return new ResponseEntity<>(postLikeService.countLikes(postId), HttpStatus.OK);
    }

    // 게시물 좋아요 여부 출력
    @GetMapping("/posts/{postId}/likes")
    public ResponseEntity<Boolean> isLiked(
            @PathVariable Long postId,
            @SessionAttribute(name = "sessionResponseDto") SessionResponseDto sessionResponseDto
    ) {
        return new ResponseEntity<>(postLikeService.isLiked(postId, sessionResponseDto.getId()), HttpStatus.OK) ;
    }
}
