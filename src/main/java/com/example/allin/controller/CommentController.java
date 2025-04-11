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
    @PostMapping
    public CommentResponseDto createComment(@RequestBody CommentRequestDto dto) {
        return commentService.createComment(dto, getMockUser());
    }
    @PutMapping("/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id,
                                            @RequestBody CommentRequestDto dto) {
        return commentService.updateComment(id, dto, getMockUser());
    }
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id, getMockUser());
    }

    @PostMapping("/{id}/like")
    public String likeOrUnlikeComment(@PathVariable Long id) {
        return commentService.toggleLike(id, getMockUser());
    }
    @GetMapping
    public List<CommentResponseDto> getAllComments() {
        return commentService.getAllComments();
    }
}
