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
public class ParticipantDto {
    private String username;
    private Integer score;

    public ParticipantDto(Participant participant) {
        this.username = participant.getPlayer().getUsername();
        this.score = participant.getScore();
    }

}
