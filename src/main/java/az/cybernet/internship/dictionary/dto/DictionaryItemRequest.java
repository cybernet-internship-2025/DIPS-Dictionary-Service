package az.cybernet.internship.dictionary.dto;

import jakarta.validation.constraints.NotNull;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class DictionaryItemRequest {
    private UUID id;
    private String category;
    private String value;
    private String description;
    private boolean isActive = true;
    private LocalDateTime deletedAt;
}
