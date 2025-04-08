package com.example.allin.dto;

import com.example.allin.entity.FriendEntity;
import com.example.allin.entity.FriendStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FriendResponseDto {
    private Long friendID;
    private Long fromUserId;
    private Long toUserId;
    private FriendStatus status;
    private LocalDateTime createdAt;

    public FriendResponseDto(FriendEntity friendEntity){
        this.friendID = friendEntity.getFriendId();
        this.fromUserId = friendEntity.getFromUserId();
        this.toUserId = friendEntity.getToUserId();
        this.status = friendEntity.getStatus();
        this.createdAt = friendEntity.getCreatedAt();
    }
}
