package network.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "feed")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feed implements Serializable {
    @Id
    private String id;
    private String userId;
    private String postId;
}