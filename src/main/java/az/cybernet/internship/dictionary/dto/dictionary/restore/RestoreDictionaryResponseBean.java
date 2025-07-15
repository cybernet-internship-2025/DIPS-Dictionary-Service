package az.cybernet.internship.dictionary.dto.dictionary.restore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestoreDictionaryResponseBean {
    UUID id;
    String value;
    String description;
    String createdAt;
    String categoryId;
    String message;
}
