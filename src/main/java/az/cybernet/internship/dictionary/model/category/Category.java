package az.cybernet.internship.dictionary.model.category;

import az.cybernet.internship.dictionary.model.dictionary.Dictionary;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    UUID id;
    String name;
    String description;
    Boolean isActive;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;
    List<Dictionary>dictionaries;
}

