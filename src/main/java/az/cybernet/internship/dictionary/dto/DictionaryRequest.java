package az.cybernet.internship.dictionary.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class DictionaryRequest implements Serializable {
    private UUID id;
    private String category;
    private String value;
    private String description;
    private boolean isActive = true;
    private LocalDateTime deletedAt;
}
