package com.example.allin.service;

import com.example.allin.dto.PostRequestDto;
import com.example.allin.dto.PostResponseDto;
import com.example.allin.entity.Post;
import com.example.allin.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // 생성자 자동 생성/주입
public class PostService {
    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto requestDto, Long userId) {
        User findUser = postRepository.findUserByIdOrElseThrow(userId);
        Post savedPost = postRepository.save(new Post(requestDto, findUser));
        return new PostResponseDto(savedPost);
    }
}
