package com.example.allin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name ="user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)//사용자 아이디 중복 이게 걸러줌
    private String email;

    @Column(nullable = false)
    private String password;

    //아무것도 안 쓰면 nullable = true
    @Column
   private LocalDateTime deletedAt;

    @Column
    private boolean is_deleted =false;



    public User() {

    }

    public User(String username, String email, String password){
        this.username=username;
        this.email=email;
        this.password=password;

    }

    public void editPassword (String password){
        this.password=password;
    }

    public void softDelete(){
        this.is_deleted = true;
        this.deletedAt= LocalDateTime.now();
    }


}


