package az.cybernet.internship.dictionary.dto.category.restoreCategory;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestoreCategoryRequestBean {
    @NotNull(message = "ID is required!")
    UUID id;
}
