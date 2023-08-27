package dreamgames.backendtask.monolith.repositories;

import dreamgames.backendtask.monolith.model.entity.Participant;
import dreamgames.backendtask.monolith.model.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    List<Tournament> findAllByStartedIsFalse();
    List<Tournament> findAllByStartedIsTrue();
    Tournament findTournamentByParticipantsContaining(Participant id);
}
