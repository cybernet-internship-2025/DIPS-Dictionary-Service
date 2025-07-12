package az.cybernet.internship.dictionary.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

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

