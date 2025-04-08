package com.example.allin.service;

import com.example.allin.dto.*;
import com.example.allin.entity.*;
import com.example.allin.repository.*;
import com.example.allin.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepo;
    private final PostRepository postRepo;
    private final UserRepository userRepo;

    public CommentResponseDto create(Long postId, String username, CommentRequestDto dto) {
        Comment comment = Comment.builder()
                .user(findUser(username))
                .post(findPost(postId))
                .commentContent(dto.commentContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        commentRepo.save(comment);
        return toDto(comment);
    }

    public CommentResponseDto get(Long id) {
        return toDto(findComment(id));
    }

    public CommentResponseDto update(Long id, String username, CommentRequestDto dto) {
        Comment comment = findComment(id);
        validate(comment, username);
        comment.update(dto.commentContent());
        return toDto(comment);
    }

    public String delete(Long id, String username) {
        Comment comment = findComment(id);
        validate(comment, username);
        commentRepo.delete(comment);
        return "댓글이 삭제되었습니다.";
    }

    public String likeComment(Long commentId) {
        Comment comment = findComment(commentId);
        comment.like();
        commentRepo.save(comment);
        return "좋아요";
    }

    public String unlikeComment(Long commentId) {
        Comment comment = findComment(commentId);
        comment.unlike();
        commentRepo.save(comment);
        return "좋아요 취소";
    }

    private User findUser(String username) {
        return userRepo.findByUserName(username).orElseThrow(() -> new NotFoundException("사용자 없음"));
    }

    private Post findPost(Long postId) {
        return postRepo.findById(postId).orElseThrow(() -> new NotFoundException("게시글 없음"));
    }

    private Comment findComment(Long id) {
        return commentRepo.findById(id).orElseThrow(() -> new NotFoundException("댓글 없음"));
    }

    private void validate(Comment comment, String username) {
        boolean isWriter = comment.getUser().getUserName().equals(username);
        boolean isPostOwner = comment.getPost().getUser().getUserName().equals(username);
        if (!isWriter && !isPostOwner)
            throw new SecurityException("권한 없음");
    }

    private CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(comment.getUser().getUserName(), comment.getCommentContent());
    }
}
