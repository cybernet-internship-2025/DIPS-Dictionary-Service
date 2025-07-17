package az.cybernet.internship.dictionary.exception;

import az.cybernet.internship.dictionary.dto.resp.ErrorResp;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DictionaryNotFoundException.class)
    public ResponseEntity<ErrorResp> handleDictionaryNotFound(DictionaryNotFoundException ex) {
        ErrorResp error = new ErrorResp(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InputValueMissingException.class)
    public ResponseEntity<ErrorResp> handleInputValueMissing(InputValueMissingException ex) {
        ErrorResp error = new ErrorResp(
                HttpStatus.BAD_REQUEST.value(),
                "Input not Acceptable",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public  ResponseEntity<ErrorResp> handleDuplicateKeyValue(DuplicateKeyException ex) {
        ErrorResp error = new ErrorResp(
                HttpStatus.BAD_REQUEST.value(),
                "Duplicate key value",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PSQLException.class)
    public  ResponseEntity<ErrorResp> handlePSQLException(PSQLException ex) {
        ErrorResp error = new ErrorResp(
                HttpStatus.BAD_REQUEST.value(),
                "Database is unhappy :(",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
