package network.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class RegisterModel {
    @JsonProperty("user_id")
    private String userId;
}
