package com.example.allin.controller;

import com.example.allin.dto.PostRequestDto;
import com.example.allin.dto.PostResponseDto;
import com.example.allin.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor // 생성자 자동 생성/주입(DI)
@RequestMapping("/posts/")
public class PostController implements PostControllerInterface {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(
            @Validated @RequestBody PostRequestDto requestDto,
            @SessionAttribute(name = "loginUser") SessionResponseDto sessionResponseDto
            ) {
        PostResponseDto responseDto = postService.createPost(requestDto, sessionResponseDto.getUserId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 여기 예외처리 필요
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> findPostsByPage(
            @RequestParam(defaultValue = "1") Long offset,
            @RequestParam(defaultValue = "10") Long limit
    ) {
        List<PostResponseDto> responseDtoList = postService.findByPage(offset, limit);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> findPostById(Long postId) {
        PostResponseDto responseDto = postService.findById(postId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable Long postId,
            @Validated @RequestBody PostRequestDto requestDto,
            @SessionAttribute(name = "loginUser") SessionResponseDto sessionResponseDto
    ) {
        /**
         * 로그인한 유저만 본인의 게시글 수정 가능
         */
        postService.validateOwner(postId, sessionResponseDto.getUserId());
        PostResponseDto responseDto = postService.updatePost(postId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    @Transactional
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            @SessionAttribute(name = "loginUser") SessionResponseDto sessionResponseDto
    ) {

        // User 테이블의 PK가 userId로 지정되어 있고 Getter가 있어야 함(통합 시 체크포인트)
        postService.validateOwner(postId, sessionResponseDto.getUserId());
        postService.delete(postId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
