package dreamgames.backendtask.monolith.model.dto;

import dreamgames.backendtask.monolith.model.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CountryLeaderBoardDto {

    private Country country;
    private Integer score;

}
