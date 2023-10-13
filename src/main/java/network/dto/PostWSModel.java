package network.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import network.entity.Post;

@Data
public class PostWSModel {
    @JsonProperty("PostId")
    private String id;

    @JsonProperty("PostText")
    private String text;

    @JsonProperty("UserId")
    private String authorUserId;

    public PostWSModel(Post userPost) {
        this.id = userPost.getId();
        this.text = userPost.getText();
        this.authorUserId = userPost.getAuthorUserId();
    }
}
