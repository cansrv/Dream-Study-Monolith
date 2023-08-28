package dreamgames.backendtask.monolith.exceptions.tournament;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Player is already queued.")
public class PlayerIsAlreadyQueuedException extends RuntimeException {
    public PlayerIsAlreadyQueuedException(String username) {
        super("Player with username " + username + " is already queued.");
    }
}
