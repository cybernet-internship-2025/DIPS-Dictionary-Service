package az.cybernet.internship.dictionary.dto.category.updateCategory;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCategoryResponseBean {
    UUID id;
    String name;
    String description;
    Boolean isActive;
    String createdAt;
    String message;
}
