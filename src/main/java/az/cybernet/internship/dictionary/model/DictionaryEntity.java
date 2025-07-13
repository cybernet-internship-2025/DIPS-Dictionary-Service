package az.cybernet.internship.dictionary.model;

import lombok.Data;
import java.util.UUID;

@Data
public class DictionaryEntity {
    private UUID id;
    private String category;
    private String value;
    private String description;
    private Boolean isActive;
    private String deletedAt;
}