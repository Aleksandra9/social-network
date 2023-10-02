package network.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import network.entity.Post;

@Data
public class PostModel {
    @JsonProperty("id")
    private String id;

    @JsonProperty("text")
    private String text;

    @JsonProperty("author_user_id")
    private String authorUserId;

    public PostModel(Post userPost) {
        this.id = userPost.getId();
        this.text = userPost.getText();
        this.authorUserId = userPost.getAuthorUserId();
    }
}
