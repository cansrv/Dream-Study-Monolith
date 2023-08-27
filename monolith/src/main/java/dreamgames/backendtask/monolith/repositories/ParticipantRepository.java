package dreamgames.backendtask.monolith.repositories;

import dreamgames.backendtask.monolith.model.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Participant findParticipantByPlayer_Username(String username);
}
