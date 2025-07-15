package az.cybernet.internship.dictionary.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NotFoundException extends RuntimeException {
    ErrorCode errorCode;

    public NotFoundException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode=errorCode;
    }

    public NotFoundException(ErrorCode errorCode,String customMessage){
        super(customMessage);
        this.errorCode=errorCode;
    }
}
