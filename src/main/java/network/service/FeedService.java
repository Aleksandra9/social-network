package network.service;

import lombok.extern.slf4j.Slf4j;
import network.dto.UserPostModel;
import network.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class FeedService {
    private final FeedRepository feedRepository;
    @Autowired
    private CacheManager cacheManager;

    public FeedService(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    @Cacheable("feed")
    public UserPostModel get(String userId) {
        var posts = feedRepository.findAllForUser(userId, new BigInteger("1000"));
        return new UserPostModel(userId, posts);
    }

    public void delete(String postId) {
        feedRepository.deletePosts(postId);
    }

    public void add(String friendId, String postId) {
        feedRepository.addPosts(friendId, postId);
    }

    public void clearCache(List<String> friends) {
        friends.forEach(Objects.requireNonNull(cacheManager.getCache("feed"))::evictIfPresent);
    }

}
