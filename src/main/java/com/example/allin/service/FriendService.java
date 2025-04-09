package com.example.allin.service;

import com.example.allin.dto.FriendRequestDto;
import com.example.allin.dto.FriendResponseDto;
import com.example.allin.entity.FriendEntity;
import com.example.allin.entity.FriendStatus;
import com.example.allin.exception.ErrorCode;
import com.example.allin.exception.FriendCustomException;
import com.example.allin.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    // 친구 요청 보내기(sendRequest), 친구 요청 수락하거나 거절(handleRequest), 친구 목록 조회(getFriends)

    // 친구 요청(sendRequest)
    @Transactional
    public FriendResponseDto sendRequest(Long fromUserId, FriendRequestDto dto){
        // 중복 확인, repository의 findByFromUserIdAndToUserId 사용.
        friendRepository.findByFromUserIdAndToUserId(fromUserId, dto.getToUserId())
                .ifPresent(friendEntity -> {
                    throw new FriendCustomException(ErrorCode.FRIEND_REQUEST_SENT);
                });

        // 새로운 친구 요청
        FriendEntity friendEntity = FriendEntity.builder()
                .fromUserId(fromUserId)
                .toUserId(dto.getToUserId())
                .status(FriendStatus.WAITING)
                .build();

        friendRepository.save(friendEntity);

        return new FriendResponseDto(friendEntity);
    }
    // 친구 요청 수락, 거절(handleRequest)
    @Transactional
    public String handleRequest(Long friendId, String action){
        FriendEntity friendEntity = friendRepository.findById(friendId)
                .orElseThrow(() -> new FriendCustomException(ErrorCode.FRIEND_NOT_FOUND));

        switch (action.toUpperCase()){
            case "CONFIRM":
                friendEntity.confirm();
                return "친구 요청을 수락했습니다.";
            case "REJECT":
                friendEntity.reject();
                return "친구 요청을 거절했습니다.";
            default:
                throw new FriendCustomException(ErrorCode.INVALID_FRIEND);
        }
    }
    // 친구 삭제(deleteFriend)
    @Transactional
    public void deleteFriend(Long friendId){
        FriendEntity friendEntity = friendRepository.findById(friendId)
                .orElseThrow(()-> new FriendCustomException(ErrorCode.FRIEND_NOT_FOUND));
        friendRepository.delete(friendEntity);
    }

    // 친구 목록 조회(getFriends), repository의 findByToUserIdAndStatus 사용.
    @Transactional
    public List<FriendResponseDto> getFriends(Long userId){
        return  friendRepository.findByToUserIdAndStatus(userId, FriendStatus.CONFIRM)
                .stream()
                .map(FriendResponseDto::new)
                .collect(Collectors.toList());
    }

}
