package network.service;

import lombok.extern.slf4j.Slf4j;
import network.dto.PostCreateBodyModel;
import network.dto.PostUpdateBodyModel;
import network.entity.Post;
import network.repository.PostRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final FeedService feedService;
    private final FriendService friendService;

    public PostService(PostRepository postRepository, FeedService feedService, FriendService friendService) {
        this.postRepository = postRepository;
        this.feedService = feedService;
        this.friendService = friendService;
    }

    @Cacheable(value = "posts")
    @Transactional
    public Post create(String userId, PostCreateBodyModel body) {
        if (Objects.isNull(body.getText()) || body.getText().isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Невалидные данные");
        var post = postRepository.saveAndFlush(Post.builder()
                .id(UUID.randomUUID().toString())
                .authorUserId(userId)
                .text(body.getText())
                .createDatetime(LocalDateTime.now())
                .build());
        feedService.add(userId, post.getId());
        feedService.clearCache(friendService.getFriends(userId));
        return post;
    }

    @CacheEvict("posts")
    @Transactional
    public Post delete(String userId, String id) {
        var postOpt = postRepository.findById(id);
        if (postOpt.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Невалидные данные");

        var post = postOpt.get();
        if (Objects.nonNull(post.getDeleteDatetime()) || !post.getAuthorUserId().equals(userId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Невалидные данные");
        post.setDeleteDatetime(LocalDateTime.now());
        post = postRepository.save(post);

        feedService.delete(post.getId());
        feedService.clearCache(friendService.getFriends(userId));
        return post;
    }

    @Cacheable(value = "posts")
    public Post getId(String id) {
        return postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Невалидные данные"));
    }

    @Cacheable(value = "posts", key = "#body.id")
    public Post update(String userId, PostUpdateBodyModel body) {
        var postOpt = postRepository.findById(body.getId());
        if (postOpt.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Невалидные данные");

        var post = postOpt.get();
        if (!post.getAuthorUserId().equals(userId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Невалидные данные");
        post.setText(body.getText());
        post.setUpdateDatetime(LocalDateTime.now());
        return postRepository.save(post);
    }
}
