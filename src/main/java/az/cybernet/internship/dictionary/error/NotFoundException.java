package az.cybernet.internship.dictionary.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NotFoundException extends RuntimeException {
    static ErrorCode errorCode = ErrorCode.NOT_FOUND;

    public NotFoundException(String entityName, Object id) {
        super(String.format("%s not found with id: %s", entityName, id));
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }
}
