package az.cybernet.internship.dictionary.model.dictionary;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Dictionary {
    private UUID id;
    private String value;
    private String description;
    private Boolean isActive;
    private LocalDateTime deletedAt;

    // ManyToOne əlaqə
    private String categoryId;
}

