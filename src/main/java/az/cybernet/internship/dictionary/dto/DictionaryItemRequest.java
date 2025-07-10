package az.cybernet.internship.dictionary.dto;

import jakarta.validation.constraints.NotNull;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DictionaryItemRequest {
    private Long id;
    private String category;
    private String value;
    private String description;
    private boolean isActive = true;
    private LocalDateTime deletedAt;
}
