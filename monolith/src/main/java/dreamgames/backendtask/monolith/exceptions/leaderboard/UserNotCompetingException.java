package dreamgames.backendtask.monolith.exceptions.leaderboard;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "Player is not competing.")

public class UserNotCompetingException extends RuntimeException {

        public UserNotCompetingException(String username) {
            super("Player with username " + username + " is not competing.");
        }
}
