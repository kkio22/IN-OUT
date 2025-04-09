package com.example.allin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class FriendRequestDto {

    @NotNull(message= "보낼 사용자 ID를 확인하세요.")
    private Long fromUserId;

    @NotNull(message = "받을 사용자 ID를 확인하세요.")
    private Long toUserId;
}
