package com.example.allin.service;

import com.example.allin.dto.CommentRequestDto;
import com.example.allin.dto.CommentResponseDto;
import com.example.allin.entity.Comment;
import com.example.allin.entity.CommentLike;
import com.example.allin.entity.User;
import com.example.allin.repository.CommentLikeRepository;
import com.example.allin.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        Comment comment = new Comment(requestDto.getContent(), user);
        commentRepository.save(comment);
        return new CommentResponseDto(comment, 0L);
    }

    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user) {
        Comment comment = getUserCommentOrThrow(id, user);
        comment.update(requestDto.getContent());
        Long likeCount = commentLikeRepository.countByComment(comment);
        return new CommentResponseDto(comment, likeCount);
    }

    public void deleteComment(Long id, User user) {
        Comment comment = getUserCommentOrThrow(id, user);
        commentRepository.delete(comment);
    }

    public String toggleLike(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));

        return commentLikeRepository.findByUserAndComment(user, comment)
                .map(existingLike -> {
                    commentLikeRepository.delete(existingLike);
                    return "좋아요 취소";
                })
                .orElseGet(() -> {
                    commentLikeRepository.save(new CommentLike(user, comment));
                    return "좋아요 추가";
                });
    }

    public List<CommentResponseDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(comment -> new CommentResponseDto(
                        comment,
                        commentLikeRepository.countByComment(comment)
                ))
                .collect(Collectors.toList());
    }

    private Comment getUserCommentOrThrow(Long id, User user) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("본인의 댓글만 수정 또는 삭제할 수 있습니다.");
        }
        return comment;
    }
}
