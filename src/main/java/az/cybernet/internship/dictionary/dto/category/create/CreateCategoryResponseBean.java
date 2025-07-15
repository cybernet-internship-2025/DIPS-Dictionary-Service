package az.cybernet.internship.dictionary.dto.category.create;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCategoryResponseBean {
    UUID id;
    String name;
    String description;
    Boolean isActive;
    String createdAt;
    String message;
}
