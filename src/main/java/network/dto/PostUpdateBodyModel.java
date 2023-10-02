package network.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostUpdateBodyModel {
    @JsonProperty("id")
    private String id;

    @JsonProperty("text")
    private String text;
}
