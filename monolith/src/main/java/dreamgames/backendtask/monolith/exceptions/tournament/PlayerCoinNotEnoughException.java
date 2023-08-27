package dreamgames.backendtask.monolith.exceptions.tournament;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "Player does not have enough coin.")
public class PlayerCoinNotEnoughException extends RuntimeException {
    public PlayerCoinNotEnoughException(String username) {
        super("Player with username " + username + " does not have enough coin.");
    }
}
