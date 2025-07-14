package az.cybernet.internship.dictionary.exception.handler;

import az.cybernet.internship.dictionary.dto.response.ExceptionResponseDTO;
import az.cybernet.internship.dictionary.exception.model.ConflictException;
import az.cybernet.internship.dictionary.exception.type.ExceptionType;
import az.cybernet.internship.dictionary.exception.model.NotFoundException;
import az.cybernet.internship.dictionary.exception.model.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleNotFound(NotFoundException exception) {
        return buildResponse(exception.getExceptionType().getStatus(), exception.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponseDTO> handleValidation(ValidationException exception) {
        return buildResponse(exception.getExceptionType().getStatus(), exception.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ExceptionResponseDTO> handleConflict(ConflictException exception) {
        return buildResponse(exception.getExceptionType().getStatus(), exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleOther(Exception exception) {
        return buildResponse(ExceptionType.INTERNAL_SERVER_ERROR.getStatus(), exception.getMessage());
    }

    private ResponseEntity<ExceptionResponseDTO> buildResponse(HttpStatus status, String message) {
        return new ResponseEntity<>(
                new ExceptionResponseDTO(
                        status,
                        message,
                        LocalDateTime.now()
                ),
                status
        );
    }
}