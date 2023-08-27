package dreamgames.backendtask.monolith.exceptions.tournament;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Player is already competing.")
public class PlayerIsAlreadyCompetingException extends RuntimeException {

    public PlayerIsAlreadyCompetingException(String username) {
        super("Player with username " + username + " is already competing.");
    }
}