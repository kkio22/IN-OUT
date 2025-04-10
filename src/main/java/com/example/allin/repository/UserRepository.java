package com.example.allin.repository;

import com.example.allin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // 댓글쪽에서 필요해서 추가함

}
