package com.example.allin.controller;

import com.example.allin.dto.*;
import com.example.allin.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService service;

    //댓글 작성
    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentResponseDto> create(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto dto,
            @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.status(201).body(service.create(postId, user.getUsername(), dto));
    }

    //댓글 조회 <== 필요없으면 삭제 OK
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> read(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    //댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> update(
            @PathVariable Long id,
            @RequestBody CommentRequestDto dto,
            @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(service.update(id, user.getUsername(), dto));
    }

    //댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(Map.of("msg", service.delete(id, user.getUsername())));
    }

    //댓글 좋아요
    @PutMapping("/{commentId}/like")
    public ResponseEntity<Map<String, String>> like(@PathVariable Long commentId) {
        String msg = service.likeComment(commentId);
        return ResponseEntity.ok(Map.of("msg", msg));
    }

    //댓글 좋아요 취소
    @PutMapping("/{commentId}/unlike")
    public ResponseEntity<Map<String, String>> unlike(@PathVariable Long commentId) {
        String msg = service.unlikeComment(commentId);
        return ResponseEntity.ok(Map.of("msg", msg));
    }
}
