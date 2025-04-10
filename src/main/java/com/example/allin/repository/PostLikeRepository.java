package com.example.allin.repository;

import com.example.allin.entity.Post;
import com.example.allin.entity.PostLike;
import com.example.allin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    // PostId와 UserId로 좋아요 여부 조회
    Optional<PostLike> findByPost_PostIdAndUser_Id(Long postId, Long userId);

    // 좋아요 존재 여부만 빠르게 확인하고 싶을 때
    boolean existsByPostAndUser(Post post, User user);
}
