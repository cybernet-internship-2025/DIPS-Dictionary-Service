package az.cybernet.internship.dictionary.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dictionary {
    String id;
    String category;
    String value;
    String description;
    Boolean isActive;
    LocalDateTime createDate;
    LocalDateTime updateDate;
}

