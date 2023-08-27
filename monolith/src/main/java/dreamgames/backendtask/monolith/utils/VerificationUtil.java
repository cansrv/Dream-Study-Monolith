package dreamgames.backendtask.monolith.utils;

import dreamgames.backendtask.monolith.exceptions.tournament.*;
import dreamgames.backendtask.monolith.model.dto.PlayerDto;
import dreamgames.backendtask.monolith.model.entity.Participant;
import dreamgames.backendtask.monolith.model.enums.Award;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

@Slf4j
public class VerificationUtil {
    public static void verifyTournamentConditions(PlayerDto player) {
        String username = player.getUsername();
        if(player.getLevel() < 20) {
            log.error("Player with username {} does not have the required level.", username);
            throw new PlayerLevelNotEnoughException(username);
        }
        if(player.getCoin() < 1000) {
            log.error("Player with username {} does not have enough coins.", username);
            throw new PlayerCoinNotEnoughException(username);
        }
        if(player.getIsCompeting()) {
            log.error("Player with username {} is already competing.", username);
            throw new PlayerIsAlreadyCompetingException(username);
        }
        if(!player.getAward().equals(Award.NO_AWARD)) {
            log.error("Player with username {} needs to claim reward.", username);
            throw new PlayerNeedsToClaimRewardException(username);
        }
        if(!player.getWantsToCompete()) {
            log.error("Player with username {} does not want to compete.", username);
            throw new PlayerIsAlreadyQueuedException(username);
        }

    }

    public static void verifyTournamentTime() {
        // If system time is between 20:00 and 23:59, throw exception
        LocalTime now = LocalTime.now();
        LocalTime tournamentStart = LocalTime.of(20, 0);
        LocalTime tournamentEnd = LocalTime.of(23, 59, 59, 999999999);
        if(now.isAfter(tournamentStart) && now.isBefore(tournamentEnd)) {
            log.error("Tournaments are not online between 8PM and 00:00AM.");
            throw new TournamentTimeException();
        }
    }
}
