package dreamgames.backendtask.monolith.model.entity;

import dreamgames.backendtask.monolith.model.enums.Award;
import dreamgames.backendtask.monolith.model.enums.Country;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Player class that will be used to store player data
 * @author Harun Can Surav
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "username_index", columnList = "username", unique = true)
})
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private Integer coin;
    private Integer level;
    private Boolean isCompeting;
    private Award award;
    private Boolean wantsToCompete;
    private Country country;
}
