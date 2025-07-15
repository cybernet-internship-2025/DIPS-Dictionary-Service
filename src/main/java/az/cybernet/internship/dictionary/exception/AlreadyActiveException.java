package az.cybernet.internship.dictionary.exception;

public class AlreadyActiveException extends RuntimeException {
    public AlreadyActiveException(String message) {
        super(message);
    }
}
