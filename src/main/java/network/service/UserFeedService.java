package network.service;

import lombok.extern.slf4j.Slf4j;
import network.dto.PostModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserFeedService {
    private final FeedService feedService;
    private final PostService postService;

    public UserFeedService(FeedService feedService, PostService postService) {
        this.feedService = feedService;
        this.postService = postService;
    }

    public List<PostModel> get(String userId, BigDecimal limit) {
        return feedService.get(userId).getPosts().stream().limit(limit.longValue())
                .map(el -> new PostModel(postService.getId(el)))
                .collect(Collectors.toList());
    }

}
