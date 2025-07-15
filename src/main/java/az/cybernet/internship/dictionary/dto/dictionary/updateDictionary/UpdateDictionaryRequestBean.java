package az.cybernet.internship.dictionary.dto.dictionary.updateDictionary;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateDictionaryRequestBean {
    @NotNull(message = "Dictionary ID is required!")
    UUID id;
    String value;
    String description;
    String categoryId;
}
