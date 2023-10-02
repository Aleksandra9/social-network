package network.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;


@Data
public class UserRegisterBodyModel {
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("second_name")
    private String secondName;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("birthdate")
    private LocalDate birthdate;

    @JsonProperty("biography")
    private String biography;

    @JsonProperty("city")
    private String city;

    @JsonProperty("password")
    private String password;
}
