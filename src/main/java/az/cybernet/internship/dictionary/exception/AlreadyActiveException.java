package az.cybernet.internship.dictionary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyActiveException extends RuntimeException {
    public AlreadyActiveException(String message) {
        super(message);
    }
}
