package com.example.allin.repository;

import com.example.allin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> user(String user);
}
