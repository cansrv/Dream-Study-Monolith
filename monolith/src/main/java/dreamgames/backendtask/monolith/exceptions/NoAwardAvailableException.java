package dreamgames.backendtask.monolith.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No award available.")
public class NoAwardAvailableException extends RuntimeException {
    public NoAwardAvailableException(String message) {
        super(message);
    }
}
