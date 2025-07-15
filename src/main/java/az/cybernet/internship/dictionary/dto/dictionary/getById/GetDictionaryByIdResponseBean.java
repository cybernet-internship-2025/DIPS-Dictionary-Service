package az.cybernet.internship.dictionary.dto.dictionary.getById;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetDictionaryByIdResponseBean {
    UUID id;
    String value;
    String description;
    String createdAt;
    UUID categoryId;
    String message;
}
