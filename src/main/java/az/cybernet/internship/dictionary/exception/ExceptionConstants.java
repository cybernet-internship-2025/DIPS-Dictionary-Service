package az.cybernet.internship.dictionary.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionConstants {
    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND", "no category with id was found");

    private final String code;
    private final String message;

    public String getMessage(Long id) {
        if ((this == CATEGORY_NOT_FOUND) && id != null) {
            return String.format("No %s with id (ID: %s) was found",
                    this.name().toLowerCase().replace("_not_found", ""), id);
        }
        return this.message;
    }
}
