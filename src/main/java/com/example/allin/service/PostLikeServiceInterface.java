package com.example.allin.service;

public interface PostLikeServiceInterface {
    void toggleLike(Long postId, Long userId);
    Long countLikes(Long postId);
    boolean isLiked(Long postId, Long userId);
}
