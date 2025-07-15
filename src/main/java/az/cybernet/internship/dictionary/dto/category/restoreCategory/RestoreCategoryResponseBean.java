package az.cybernet.internship.dictionary.dto.category.restoreCategory;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestoreCategoryResponseBean {
    UUID id;
    String message;
    String name;
    String description;
    String createdAt;
}
