package dreamgames.backendtask.monolith.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "started_index", columnList = "started", unique = false)
})
public class Tournament {
    @Id
    private Long id;
    @OneToMany
    private List<Participant> participants;
    private boolean started;
}
