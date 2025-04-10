package com.example.allin.service;

import com.example.allin.entity.Post;
import com.example.allin.entity.PostLike;
import com.example.allin.entity.User;
import com.example.allin.exception.PostCustomException;
import com.example.allin.repository.PostLikeRepository;
import com.example.allin.repository.PostRepository;
import com.example.allin.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.allin.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostLikeService implements PostLikeServiceInterface{
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final UserRepository userRepository;


    public void toggleLike(Long postId, Long userId) {
        Optional<PostLike> postLike = postLikeRepository.findByPost_PostIdAndUser_Id(postId, userId);

        if (postLike.isPresent()) {
            //좋아요 취소
            postLikeRepository.delete(postLike.get());
        } else {
            // 좋아요 등록
            Post post = postRepository.findByIdOrElseThrow(postId);
            User user = userRepository.findById(userId)
                    .orElseThrow(()-> new PostCustomException(USER_NOT_FOUND));
            postLikeRepository.save(new PostLike(post, user));
        }
    }

    @Override
    public Long countLikes(Long postId) {
        return postLikeRepository.countAllByPost_PostId(postId);
    }

    @Override
    public boolean isLiked(Long postId, Long userId) {
        return postLikeRepository.existsByPost_PostIdAndUser_id(postId, userId);
    }
}
