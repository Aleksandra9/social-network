package network.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post implements Serializable {
    @Id
    private String id;
    private String authorUserId;
    private String text;
    private LocalDateTime createDatetime;
    private LocalDateTime updateDatetime;
    private LocalDateTime deleteDatetime;
}