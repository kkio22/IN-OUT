package com.example.allin.service;

import com.example.allin.dto.*;
import com.example.allin.entity.*;
import com.example.allin.exception.CommentLikeNotFoundException;
import com.example.allin.exception.CommentNotFoundException;
import com.example.allin.exception.ErrorCode;
import com.example.allin.repository.*;
import com.example.allin.exception.CommentUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentLikeRepository commentLikeRepository;

    public CommentResponseDto create(CommentRequestDto dto) {
        Comment comment = Comment.builder()
                .commentContent(dto.commentContent())
                .likeCount(0)
                .build();
        commentRepository.save(comment);
        return toDto(comment);
    }

    public CommentResponseDto read(Long id) {
        return toDto(find(id));
    }

    public CommentResponseDto update(Long id, CommentRequestDto dto) {
        Comment comment = find(id);
        comment.update(dto.commentContent());
        return toDto(comment);
    }

    public void delete(Long id) {
        commentRepository.delete(find(id));
    }

    public String like(Long id, UserDetails userDetails) {
        Comment comment = find(id);
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new CommentUserNotFoundException(ErrorCode.COMMENT_USER_NOT_FOUND));

        if (commentLikeRepository.findByUserAndComment(user, comment).isPresent()) {
            return "이미 좋아요한 댓글입니다.";
        }

        commentLikeRepository.save(CommentLike.builder().user(user).comment(comment).build());
        comment.like();
        commentRepository.save(comment);
        return "좋아요!";
    }

    public String unlike(Long id, UserDetails userDetails) {
        Comment comment = find(id);
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new CommentUserNotFoundException(ErrorCode.COMMENT_USER_NOT_FOUND));

        CommentLike like = commentLikeRepository.findByUserAndComment(user, comment)
                .orElseThrow(() -> new CommentLikeNotFoundException(ErrorCode.COMMENT_LIKE_NOT_FOUND));

        commentLikeRepository.delete(like);
        comment.unlike();
        commentRepository.save(comment);
        return "좋아요 취소";
    }

    private Comment find(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(ErrorCode.COMMENT_NOT_FOUND));
    }

    private CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getUser().getUsername(),
                comment.getCommentContent(),
                comment.getLikeCount()
        );
    }
}
