package az.cybernet.internship.dictionary.error;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public enum ErrorCode {

    NOT_FOUND("ERR-404", "Resource not found"),
    VALIDATION_ERROR("ERR-400", "Validation failed"),
    INTERNAL_ERROR("ERR-500", "Internal server error");

    String message;
    String code;

    ErrorCode(String code,String message){
        this.code=code;
        this.message=message;
    }
}
