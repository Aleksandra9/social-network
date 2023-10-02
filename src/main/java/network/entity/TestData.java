package network.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "test_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long intValue;
    private String charValue;

}