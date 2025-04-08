package com.example.allin.controller;

import com.example.allin.dto.FriendRequestDto;
import com.example.allin.dto.FriendResponseDto;
import com.example.allin.entity.FriendEntity;
import com.example.allin.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 친구 요청 보내기
    @PostMapping("/friends")
    public ResponseEntity<String> sendRequest(
            @RequestBody FriendRequestDto dto){
        friendService.sendRequest(dto.getFromUserId(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("친구 요청을 보냈습니다.");
    }



    // 친구 요청 수락하거나 거절(handleRequest)
    @PostMapping("/friends/{friendId}")
    public ResponseEntity<String> handleRequest(
            @PathVariable Long friendId,
            @RequestBody String response){
        if(!response.equalsIgnoreCase("confirm")){
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
        friendService.handleRequest(friendId, response);
        return ResponseEntity.ok("친구 요청을 수락했습니다.");
    }

    // 친구 삭제(deleteFriend)
    @DeleteMapping("/friends/{friendId}")
    public ResponseEntity<String> deleteFriend(
            @PathVariable Long friendId){
        friendService.deleteFriend(friendId);
        return ResponseEntity.ok("친구 목록을 삭제했습니다.");
    }

    // 친구 목록 조회(getFriends)

}
