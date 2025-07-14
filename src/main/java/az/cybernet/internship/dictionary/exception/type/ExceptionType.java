package az.cybernet.internship.dictionary.exception.type;

import org.springframework.http.HttpStatus;

public enum ExceptionType {
    ENTRY_NOT_FOUND("Entry not found", HttpStatus.NOT_FOUND),
    ENTRY_ALREADY_ACTIVE("Entry already active", HttpStatus.CONFLICT),
    BAD_REQUEST("Bad request", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    private final String message;
    private final HttpStatus status;

    ExceptionType(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
