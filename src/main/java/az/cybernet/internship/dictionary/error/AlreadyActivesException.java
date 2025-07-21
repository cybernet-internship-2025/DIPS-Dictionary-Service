package az.cybernet.internship.dictionary.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AlreadyActivesException extends RuntimeException{
    static ErrorCode errorCode = ErrorCode.ALREADY_ACTIVE;

    public AlreadyActivesException(){
        super(errorCode.getMessage());
    }

    public AlreadyActivesException(String customMessage){
        super(customMessage);
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }
}
