package az.cybernet.internship.dictionary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class DictionaryCategory {
    Long id;
    String name;
    String description;
    Boolean is_active;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<DictionaryItem> items;
}
