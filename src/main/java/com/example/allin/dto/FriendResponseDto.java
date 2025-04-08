package com.example.allin.dto;

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
}
