package az.cybernet.internship.dictionary.model;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dictionary {
    private UUID id;
    private String value;
    private String description;
    private Boolean isActive;
    private LocalDateTime deletedAt;
}