package az.cybernet.internship.dictionary.dto.dictionary.softDelete;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SoftDeleteDictionaryRequestBean {
    @NotNull(message = "Dictionary ID is required!")
    UUID id;
}
