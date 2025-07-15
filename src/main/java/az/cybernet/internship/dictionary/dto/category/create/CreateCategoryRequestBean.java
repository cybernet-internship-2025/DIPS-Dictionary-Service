package az.cybernet.internship.dictionary.dto.category.create;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCategoryRequestBean {
    UUID id;
    @NotBlank(message = "Name is required!")
    String name;
    @NotBlank(message = "Description is required!")
    String description;
}
