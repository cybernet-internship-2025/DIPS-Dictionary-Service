package az.cybernet.internship.dictionary.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@ToString(exclude = "items")
@Builder
@FieldDefaults(level = PRIVATE)
public class DictionaryCategory {
    Long id;
    String name;
    String description;
    Boolean isActive;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<DictionaryItem> items;
}
