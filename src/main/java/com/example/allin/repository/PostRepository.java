package com.example.allin.repository;

import com.example.allin.entity.Post;
import com.example.allin.entity.User;
import com.example.allin.exception.PostCustomException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.example.allin.exception.ErrorCode.POST_NOT_FOUND;

public interface PostRepository extends JpaRepository<Post, Long> {
    //postId로 DB에 있는 Post 조회할 때 사용
    default Post findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new PostCustomException(POST_NOT_FOUND));
    }

    List<Post> findAllByUser_Id(Long userId);

    void deleteAllByUser(User user);
}