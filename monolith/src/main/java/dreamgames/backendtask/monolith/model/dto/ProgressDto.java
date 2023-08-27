package dreamgames.backendtask.monolith.model.dto;

import dreamgames.backendtask.monolith.model.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProgressDto {
    private String username;
    private Integer coin;
    private Integer level;

    public ProgressDto(Player player) {
        this.username = player.getUsername();
        this.coin = player.getCoin();
        this.level = player.getLevel();
    }
}
