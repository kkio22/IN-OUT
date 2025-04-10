package com.example.allin.service;

import com.example.allin.dto.PostRequestDto;
import com.example.allin.dto.PostResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostServiceInterface {
    PostResponseDto createPost(PostRequestDto requestDto, Long userId);
    List<PostResponseDto> findByPage(Long offset, Long limit);
    PostResponseDto findById(Long id);
    List<PostResponseDto> findAllPostByUser(Long userId);
    PostResponseDto updatePost(Long postId, PostRequestDto requestDto);
    void delete(Long postId);
    void validateOwner(Long postId, Long currentUserId); // 게시물 작성자-유저 여부 검증용
}
