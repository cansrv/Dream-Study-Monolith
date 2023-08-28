package dreamgames.backendtask.monolith.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Player with username already exists.")
public class PlayerAlreadyExistsException extends RuntimeException {

    public PlayerAlreadyExistsException(String username) {
        super("Player with username " + username + " already exists.");
    }
}
