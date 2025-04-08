package com.example.allin.service;

import com.example.allin.dto.FriendRequestDto;
import com.example.allin.dto.FriendResponseDto;
import com.example.allin.entity.FriendEntity;
import com.example.allin.entity.FriendStatus;
import com.example.allin.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    // 친구 요청 보내기(sendRequest), 친구 요청 수락하거나 거절(handleRequest), 친구 목록 조회(getFriends)

    // 친구 요청(sendRequest)
    @Transactional
    public FriendResponseDto sendRequest(Long fromUserId, FriendRequestDto dto){
        // 중복 확인
        Optional<FriendEntity> test= Optional.ofNullable(friendRepository
                .findByFromUserIdAndToUserId(fromUserId, dto.getToUserId())
                .orElseThrow(() -> new IllegalArgumentException("중복된 사용자 입니다.")));

        // 새로운 친구 요청
        FriendEntity friendEntity = FriendEntity.builder()
                .fromUserId(fromUserId)
                .toUserId(dto.getToUserId())
                .status(FriendStatus.WAITING)
                .build();

        friendRepository.save(friendEntity);

        return new FriendResponseDto(test.get());
    }
    // 친구 요청 수락, 거절(handleRequest)
    @Transactional
    public String handleRequest(Long friendId, String action){
        FriendEntity friendEntity = friendRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 요청입니다."));

        switch (action.toUpperCase()){
            case "CONFIRM":
                friendEntity.confirm();
                return "친구 요청을 수락했습니다.";
            case "REJECT":
                friendEntity.reject();
                return "친구 요청을 거절했습니다.";
            default:
                throw new IllegalArgumentException("잘못된 요청 상황입니다.");
        }
    }
    // 친구 목록 조회(getFriends)
    public List<FriendResponseDto> getFriends(Long userId){
        return  friendRepository.findByToUserIdAndStatus(userId, FriendStatus.CONFIRM)
                .stream()
                .map(FriendResponseDto::new)
                .collect(Collectors.toList());
    }

}
