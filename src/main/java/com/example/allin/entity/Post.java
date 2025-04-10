package com.example.allin.entity;

import com.example.allin.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "post")
@Getter
public class Post extends BaseEntity{

    // 게시물 고유 id(PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY) // schedule.getUser(); 할 때 쿼리 발생시키기 위함
    @JoinColumn(name= "user_id")
    private User user;

    //게시글 제목
    @Column(nullable = false)
    private String title;

    //게시물 내용
    @Column(nullable = false, columnDefinition = "longtext")
    private String postContent;

    /**
     * createAt / modifiedAt은 BaseEntity 상속
     */

    //기본생성자
    public Post(){
    }

    /**
     * @RequestBody requestDto
     * @SessionAttribute(name = Const.LOGIN_USER) Long userId에서 유저 Id 받아서 DB에서 유저 조회하고
     * 있으면 아래 Post 생성자 호출해 Post 생성
     */
    public Post(PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.postContent = requestDto.getPostContent();
        this.user = user;
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.postContent = requestDto.getPostContent();
    }
}
