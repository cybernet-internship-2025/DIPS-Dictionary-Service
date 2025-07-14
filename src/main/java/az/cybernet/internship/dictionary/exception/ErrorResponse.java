package az.cybernet.internship.dictionary.exception;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class ErrorResponse {
    String code;
    String message;
    List<ValidationException> validationExceptions;
}
