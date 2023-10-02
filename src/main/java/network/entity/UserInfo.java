package network.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "user_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
    @Id
    private String id;
    private String firstName;
    private String secondName;
    private Integer age;
    private LocalDate birthdate;
    private String biography;
    private String city;
    private String password;
}