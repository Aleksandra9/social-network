package network.service;

import lombok.extern.slf4j.Slf4j;
import network.entity.Friend;
import network.repository.FriendRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class FriendService {
    private final FriendRepository friendRepository;
    private final FeedService feedService;

    public FriendService(FriendRepository friendRepository, FeedService feedService) {
        this.friendRepository = friendRepository;
        this.feedService = feedService;
    }

    public void setFriend(String userId, String friendId) {
        var friend = friendRepository.findByUserIdAndFriendId(userId, friendId);
        if (friend.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Невалидные данные");
        friendRepository.save(Friend.builder()
                .userId(userId)
                .friendId(friendId)
                .createDatetime(LocalDateTime.now())
                .build());
        feedService.clearCache(userId);
    }

    public void deleteFriend(String userId, String friendId) {
        var friendOpt = friendRepository.findByUserIdAndFriendId(userId, friendId);
        if (friendOpt.isEmpty() || Objects.nonNull(friendOpt.get().getDeleteDatetime()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Невалидные данные");

        var friend = friendOpt.get();
        friend.setDeleteDatetime(LocalDateTime.now());
        friendRepository.save(friend);
        feedService.clearCache(userId);
    }

    public List<String> getFriends(String userId) {
        return friendRepository.getFriends(userId);
    }
}
