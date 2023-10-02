package network.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
public class UserPostModel implements Serializable {
    private String userId;
    private List<String> posts;
}
