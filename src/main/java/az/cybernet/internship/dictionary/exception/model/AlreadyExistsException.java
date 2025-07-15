package az.cybernet.internship.dictionary.exception.model;

import az.cybernet.internship.dictionary.exception.type.ExceptionType;

public class AlreadyExistsException extends RuntimeException {
    private final ExceptionType exceptionType;

    public AlreadyExistsException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }
}
