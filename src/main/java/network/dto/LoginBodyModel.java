package network.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class LoginBodyModel {
    @JsonProperty("id")
    private String id;

    @JsonProperty("password")
    private String password;
}
