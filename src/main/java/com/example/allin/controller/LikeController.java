package com.example.allin.controller;

import com.example.allin.dto.SessionResponseDto;
import com.example.allin.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final PostLikeService postLikeService;

    @PutMapping("/posts/{postId}")
    public ResponseEntity<Void> postLike(
            @PathVariable Long postId,
            @SessionAttribute(name = "sessionResponseDto")SessionResponseDto sessionResponseDto
            ) {
        postLikeService.like(postId, sessionResponseDto.getId());
        return ResponseEntity.ok().build();
    }
}
