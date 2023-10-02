package network.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import network.entity.UserInfo;

import java.time.LocalDate;


@Data
public class UserModel {
    @JsonProperty("id")
    private String id;

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

    public UserModel(UserInfo userInfo) {
        this.id = userInfo.getId();
        this.firstName = userInfo.getFirstName();
        this.secondName = userInfo.getSecondName();
        this.age = userInfo.getAge();
        this.birthdate = userInfo.getBirthdate();
        this.biography = userInfo.getBiography();
        this.city = userInfo.getCity();
    }
}
