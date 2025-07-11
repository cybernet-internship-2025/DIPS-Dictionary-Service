package az.cybernet.internship.dictionary.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DictionaryEntry {
    private UUID id;
    private String value;
    private String description;
    private boolean isActive;
    private LocalDateTime deletedAt;
    private UUID categoryId;
}
