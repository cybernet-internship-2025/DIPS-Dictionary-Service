package az.cybernet.internship.dictionary.dto.category.updateCategory;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCategoryRequestBean {
    @NotNull(message = "Category ID is required!")
    UUID id;
    String name;
    String description;
}
