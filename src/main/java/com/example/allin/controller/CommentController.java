package com.example.allin.controller;

import com.example.allin.dto.CommentRequestDto;
import com.example.allin.dto.CommentResponseDto;
import com.example.allin.entity.User;
import com.example.allin.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentResponseDto createComment(@RequestBody CommentRequestDto dto,
                                            @AuthenticationPrincipal User user) {
        return commentService.createComment(dto, user);
    }

    @PutMapping("/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id,
                                            @RequestBody CommentRequestDto dto,
                                            @AuthenticationPrincipal User user) {
        return commentService.updateComment(id, dto, user);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id,
                              @AuthenticationPrincipal User user) {
        commentService.deleteComment(id, user);
    }

    @PostMapping("/{id}/like")
    public String likeOrUnlikeComment(@PathVariable Long id,
                                      @AuthenticationPrincipal User user) {
        return commentService.toggleLike(id, user);
    }

    @GetMapping
    public List<CommentResponseDto> getAllComments() {
        return commentService.getAllComments();
    }
}
