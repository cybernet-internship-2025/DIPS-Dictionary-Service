package az.cybernet.internship.dictionary.dto.dictionary.updateDictionary;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateDictionaryResponseBean {
    UUID id;
    String value;
    String description;
    String createdAt;
    UUID categoryId;
    String message;
}
