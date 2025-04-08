package com.example.allin.service;

import com.example.allin.dto.PostRequestDto;
import com.example.allin.dto.PostResponseDto;
import com.example.allin.entity.Post;
import com.example.allin.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor // 생성자 자동 생성/주입
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponseDto createPost(PostRequestDto requestDto, Long userId) {
        User findUser = postRepository.findUserByIdOrElseThrow(userId);
        Post savedPost = postRepository.save(new Post(requestDto, findUser));
        return new PostResponseDto(savedPost);
    }

    public List<PostResponseDto> findByPage(Long offset, Long limit) {
        /**
         * PageRequest.of(page, size)
         * page는 0부터 시작하므로, 입력한 page번호(offset)에서 1을 빼줌
         */
        Pageable pageable = PageRequest.of(offset.intValue()-1, limit.intValue()); // 페이지는 0부터 시작
        Page<Post> page = postRepository.findAll(pageable); // findAll(Pageable pageable) 메서드는 JpaRepository가 기본 제공!

        // Entity > Dto 변환
        return page.stream()
                .map(PostResponseDto::new)
                .toList();
    }

    public PostResponseDto findById(Long postId) {
        Post findPost = postRepository.findByIdOrElseThrow(postId);
        return new PostResponseDto(findPost);
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto) {
        Post findPost = postRepository.findByIdOrElseThrow(postId);
        findPost.update(requestDto); // @Transactional로 인해 DirtyChecking으로 변경 자동 감지 -> DB 자동 업데이트 이때 @LastModifiedAt도 자동 갱신
        return new PostResponseDto(findPost);
    }

    public void delete(Long postId) {
        Post findPost = postRepository.findByIdOrElseThrow(postId);
        postRepository.delete(findPost);
    }

    /**
     *
     * @param postId -> Controller에서 PathVariable로 받은 값
     * @param currentUserId -> HttpServletRequst > Session > UserResponseDto에서 뽑아온 현재 로그인한 유저 Id
     */
    public void validateOwner(Long postId, Long currentUserId) {
        Post findPost = postRepository.findByIdOrElseThrow(postId);
        if (!findPost.getUser().getUserId().equals(currentUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정 권한이 없습니다.");
        }

    }
}
