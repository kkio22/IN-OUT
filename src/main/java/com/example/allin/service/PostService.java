package com.example.allin.service;

import com.example.allin.dto.PostRequestDto;
import com.example.allin.dto.PostResponseDto;
import com.example.allin.entity.Post;
import com.example.allin.entity.User;
import com.example.allin.exception.ErrorCode;
import com.example.allin.exception.PostCustomException;
import com.example.allin.exception.NotExistingUserException;
import com.example.allin.repository.PostLikeRepository;
import com.example.allin.repository.PostRepository;
import com.example.allin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.allin.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor // 생성자 자동 생성/주입
public class PostService implements PostServiceInterface {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    @Override
    public PostResponseDto createPost(PostRequestDto requestDto, Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotExistingUserException(USER_NOT_FOUND));
        Post savedPost = postRepository.save(new Post(requestDto, findUser));
        return new PostResponseDto(savedPost);
    }

    @Override
    public List<PostResponseDto> findByPage(Long offset, Long limit) {
        /**
         * PageRequest.of(page, size)
         * page는 0부터 시작하므로, 입력한 page번호(offset)에서 1을 빼줌
         */
        Pageable pageable = PageRequest.of(
                offset.intValue()-1, limit.intValue(), Sort.by(Sort.Direction.DESC, "createdAt")); // 페이지는 0부터 시작
        Page<Post> page = postRepository.findAll(pageable); // findAll(Pageable pageable) 메서드는 JpaRepository가 기본 제공!

        // Entity > Dto 변환
        return page.stream()
                .map(PostResponseDto::new)
                .toList();
    }

    /**
     *
     * @param postId 조회하는 postId
     * * @return
     */
    @Override
    public PostResponseDto findById(Long postId) {
        Post findPost = postRepository.findByIdOrElseThrow(postId);

        if(findPost.getUser().isDeleted()){
            throw new PostCustomException (ErrorCode.POST_NOT_FOUND);
        }

        return new PostResponseDto(findPost);
    }


    /**
     *
     * @param userId ... 유저 조회 화면에서, 유저별 게시물 리스트 보여주는 메서드 (User에서 GetMapping할 때 이 메서드 갖다 쓰도록 설계)
     *               **userId가 없는 경우의 예외는 user단에서 처리한 것으로 가정**
     * @return
     */
    @Override
    public List<PostResponseDto> findAllPostByUser(Long userId) {
        //userId 존재 여부 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new PostCustomException(USER_NOT_FOUND));

        if(user.isDeleted()){
            throw new PostCustomException(ErrorCode.POST_NOT_FOUND);
        }

            List<Post> postList = postRepository.findAllByUser_Id(userId);
        return postList.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto) {
        Post findPost = postRepository.findByIdOrElseThrow(postId);
        findPost.update(requestDto); // @Transactional로 인해 DirtyChecking으로 변경 자동 감지 -> DB 자동 업데이트 이때 @LastModifiedAt도 자동 갱신
        return new PostResponseDto(findPost);
    }

    @Transactional
    @Override
    public void delete(Long postId) {
        Post findPost = postRepository.findByIdOrElseThrow(postId);
        postRepository.delete(findPost);
    }

    /**
     *
     * @param postId -> Controller에서 PathVariable로 받은 값
     * @param currentUserId -> @SessionAttribute(name = Const.LOGIN_USER) Long userId 에서 받아온 로그인한 userId
     */
    @Override
    public void validateOwner(Long postId, Long currentUserId) {
        Post findPost = postRepository.findByIdOrElseThrow(postId);
        if (!findPost.getUser().getId().equals(currentUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정 권한이 없습니다.");
        }

    }
}
