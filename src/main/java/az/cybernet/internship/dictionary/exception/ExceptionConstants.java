package az.cybernet.internship.dictionary.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionConstants {
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION", "unexpected exception occurred"),
    HTTP_METHOD_IS_NOT_CORRECT("HTTP_METHOD_IS_NOT_CORRECT", "http method is not correct"),
    VALIDATION_EXCEPTION("VALIDATION_EXCEPTION", "Validation exception happened"),
    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND", "no category with id was found"),
    ITEM_NOT_FOUND("ITEM_NOT_FOUND", "no item with id was found");

    private final String code;
    private final String message;

    public String getMessage(Long id) {
        if ((this == CATEGORY_NOT_FOUND || this == ITEM_NOT_FOUND) && id != null) {
            return String.format("No %s with id (ID: %s) was found",
                    this.name().toLowerCase().replace("_not_found", ""), id);
        }
        return this.message;
    }
}
