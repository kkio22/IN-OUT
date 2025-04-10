package com.example.allin.repository;

import com.example.allin.entity.FriendEntity;
import com.example.allin.entity.FriendStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// findByToUserIdAndStatus: 친구 요청 받은 ID와 Status(대기 상태)로 찾기.
// findByFromUserIdAndToUserId: 친구 요청 보낸 ID와 친구 요청 받은 ID로 찾기.

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {
    List<FriendEntity> findByToUserIdAndStatus(Long toUserId, FriendStatus status);
    Optional<FriendEntity> findByFromUserIdAndToUserId(Long fromUserId, Long toUserId);
}
