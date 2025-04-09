package com.example.allin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name ="user")
@SQLDelete(sql = "UPDATE user SET deleted_at = NOW() WHERE id=? ")
@Where(clause = "deleted_at IS NULL")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String user;

    @Column(nullable = false, unique = true)//사용자 아이디 중복 이게 걸러줌
    private String email;

    @Column(nullable = false)
    private String password;

   private LocalDateTime deletedAt;


    public User() {

    }

    public User(String username, String email, String password){
        this.user=username;
        this.email=email;
        this.password=password;

    }

    public void editPassword (String password){
        this.password=password;
    }
}
