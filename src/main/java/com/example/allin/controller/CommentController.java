package com.example.allin.controller;
import com.example.allin.dto.CommentRequestDto;
import com.example.allin.dto.CommentResponseDto;
import com.example.allin.entity.User;
import com.example.allin.repository.UserRepository;
import com.example.allin.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final UserRepository userRepository;
    private User getMockUser() {
        return userRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));
    }

    // 댓글 생성 (댓글 달기)
    @PostMapping
    public CommentResponseDto createComment(@RequestBody CommentRequestDto dto) {
        return commentService.createComment(dto, getMockUser());
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id,
                                            @RequestBody CommentRequestDto dto) {
        return commentService.updateComment(id, dto, getMockUser());
    }

    // 댓글 삭제 (해당 유저의 ID로 작성한 댓글을 삭제)
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id, getMockUser());
    }

    // 댓글에 좋아요 / 댓글에 좋아요 취소
    @PostMapping("/{id}/like")
    public String likeOrUnlikeComment(@PathVariable Long id) {
        return commentService.toggleLike(id, getMockUser());
    }

    // 모든 댓글 조회
    @GetMapping
    public List<CommentResponseDto> getAllComments() {
        return commentService.getAllComments();
    }
}