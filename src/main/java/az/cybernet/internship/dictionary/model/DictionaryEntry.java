package az.cybernet.internship.dictionary.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DictionaryEntry {
    private String id;
    private String value;
    private String description;
    private String categoryID;

    private boolean isActive;
    private LocalDateTime deletedAt;
}
