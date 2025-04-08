package com.example.allin.controller;

import com.example.allin.common.Const;
import com.example.allin.dto.PostRequestDto;
import com.example.allin.dto.PostResponseDto;
import com.example.allin.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor // 생성자 자동 생성/주입(DI)
@RequestMapping("/posts/")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(
            @RequestBody PostRequestDto requestDto,
            HttpServletRequest sevletRequest
            ) {
        HttpSession session = sevletRequest.getSession();
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        // 미 로그인 시 생성 권한 미부여
        if (loginUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
        }

        /**
         * 로그인한 유저(세선에서 받아옴)의 userId로 post 생성
         * User의 PK가 UserId로 설정되었는지 확인 필요. 그냥 id이면 에러날 수 있음
         */
        PostResponseDto responseDto = postService.createPost(requestDto, loginUser.getUserId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

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

}
