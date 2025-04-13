package com.example.allin.repository;

import com.example.allin.entity.Post;
import com.example.allin.entity.PostLike;
import com.example.allin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    // PostId와 UserId로 좋아요 여부 조회
    Optional<PostLike> findByPost_PostIdAndUser_Id(Long postId, Long userId);

    // postId의 좋아요 수 조회
    Long countAllByPost_PostId(Long postId);
    
    // 로그인 유저의 userId로 해당 postId에 좋아요를 했는지 여부 확인
    boolean existsByPost_PostIdAndUser_id(Long postId, Long userId);
}