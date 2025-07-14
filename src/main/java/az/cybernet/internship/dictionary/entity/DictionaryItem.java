package az.cybernet.internship.dictionary.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@ToString(exclude = "category")
@Builder
@FieldDefaults(level = PRIVATE)
public class DictionaryItem {
    Long id;
    String value;
    String description;
    Boolean isActive;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    DictionaryCategory category;
}
