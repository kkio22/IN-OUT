package com.example.allin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendEntity extends BaseEntity{ // BaseEntity 상속.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendId;

    @Column(nullable = false)
    private Long fromUserId;

    @Column(nullable = false)
    private Long toUserId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FriendStatus status;


    // createdAt, updateAt 은 "BaseEntity"에서 상속됨.
    public void confirm(){
        this.status = FriendStatus.CONFIRM; // BaseEntity로 부터 updatedAt 상속.
    }
    public void waiting(){
        this.status = FriendStatus.WAITING;
    }
    public void reject(){
        this.status = FriendStatus.REJECTED;
    }

}
