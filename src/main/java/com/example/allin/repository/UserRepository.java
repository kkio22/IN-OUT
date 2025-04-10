package com.example.allin.repository;

import com.example.allin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
}
