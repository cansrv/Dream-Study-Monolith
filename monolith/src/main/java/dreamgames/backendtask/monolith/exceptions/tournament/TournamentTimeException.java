package dreamgames.backendtask.monolith.exceptions.tournament;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = org.springframework.http.HttpStatus.PRECONDITION_FAILED, reason = "Tournament is not active at the moment.")
public class TournamentTimeException extends RuntimeException {

        public TournamentTimeException() {
            super("Tournament is not active at the moment.");
        }
}
