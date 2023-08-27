package dreamgames.backendtask.monolith.model.dto;

import dreamgames.backendtask.monolith.model.entity.Participant;
import dreamgames.backendtask.monolith.model.entity.Tournament;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TournamentDto {

        private ParticipantDto[] participants;

        public TournamentDto(Tournament tournament) {
                this.participants = tournament.getParticipants().stream().map(ParticipantDto::new)
                                .toArray(ParticipantDto[]::new);
        }
}
