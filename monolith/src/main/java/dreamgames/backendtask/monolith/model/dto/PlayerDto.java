package dreamgames.backendtask.monolith.model.dto;


import dreamgames.backendtask.monolith.model.entity.Player;
import dreamgames.backendtask.monolith.model.enums.Award;
import dreamgames.backendtask.monolith.model.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerDto {

    private String username;
    private Integer coin;
    private Integer level;
    private Boolean isCompeting;
    private Boolean wantsToCompete;
    private Award award;
    private Country country;

    public PlayerDto(Player player) {
        this.username = player.getUsername();
        this.coin = player.getCoin();
        this.level = player.getLevel();
        this.isCompeting = player.getIsCompeting();
        this.award = player.getAward();
        this.country = player.getCountry();
        this.wantsToCompete = player.getWantsToCompete();
    }
}
