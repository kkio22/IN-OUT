package com.example.allin.repository;

import com.example.allin.entity.Comment;
import com.example.allin.entity.CommentLike;
import com.example.allin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
    void deleteByUserAndComment(User user, Comment comment);
}
