package com.example.allin.controller;

import com.example.allin.dto.PostRequestDto;
import com.example.allin.dto.PostResponseDto;
import com.example.allin.dto.SessionResponseDto;
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
@RequestMapping("/posts")
public class PostController implements PostControllerInterface {
    private final PostService postService;

    // 게시물 생성
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(
            @Validated @RequestBody PostRequestDto requestDto,
            @SessionAttribute(name = "sessionResponseDto") SessionResponseDto sessionResponseDto
            ) {
        PostResponseDto responseDto = postService.createPost(requestDto, sessionResponseDto.getId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 여기 예외처리 필요
    // 게시물 전체 조회(페이지네이션)
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> findPostsByPage(
            @RequestParam(defaultValue = "1") Long offset,
            @RequestParam(defaultValue = "10") Long limit
    ) {
        List<PostResponseDto> responseDtoList = postService.findByPage(offset, limit);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    // 게시물 개별 조회(게시물 id)
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> findPostById(
            @PathVariable Long postId
    ) {
        PostResponseDto responseDto = postService.findById(postId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 게시물 개별 수정(게시물 id)
    @PatchMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable Long postId,
            @Validated @RequestBody PostRequestDto requestDto,
            @SessionAttribute(name = "sessionResponseDto") SessionResponseDto sessionResponseDto
    ) {
        /**
         * 로그인한 유저만 본인의 게시글 수정 가능
         */
        postService.validateOwner(postId, sessionResponseDto.getId());
        PostResponseDto responseDto = postService.updatePost(postId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 게시물 개별 삭제(게시물 id)
    @DeleteMapping("/{postId}")
    @Transactional
    public ResponseEntity<String> deletePost(
            @PathVariable Long postId,
            @SessionAttribute(name = "sessionResponseDto") SessionResponseDto sessionResponseDto
    ) {

        // User 테이블의 PK가 userId로 지정되어 있고 Getter가 있어야 함(통합 시 체크포인트)
        postService.validateOwner(postId, sessionResponseDto.getId());
        postService.delete(postId);

        return new ResponseEntity<>("게시글이 성공적으로 삭제되었습니다.", HttpStatus.OK);
    }

}
