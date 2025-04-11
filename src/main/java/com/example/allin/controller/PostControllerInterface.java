package com.example.allin.controller;

import com.example.allin.dto.PostRequestDto;
import com.example.allin.dto.PostResponseDto;
import com.example.allin.dto.SessionResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostControllerInterface {
    ResponseEntity<PostResponseDto> createPost(PostRequestDto requestDto, SessionResponseDto sessionResponseDto); // userId는 세션에서 받아옴
    ResponseEntity<List<PostResponseDto>> findPostsByPage(Long offset, Long limit);
    ResponseEntity<PostResponseDto> findPostById(Long postId);
    ResponseEntity<PostResponseDto> updatePost(Long postId, PostRequestDto requestDto, SessionResponseDto sessionResponseDto); // userId는 세션에서 받아옴
    ResponseEntity<String> deletePost(Long postId, SessionResponseDto sessionResponseDto); // userId는 세션에서 받아옴
}
