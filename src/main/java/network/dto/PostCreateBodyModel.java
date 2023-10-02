package network.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class PostCreateBodyModel {
    @JsonProperty("text")
    private String text;
}
