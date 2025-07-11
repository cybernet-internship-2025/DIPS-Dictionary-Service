package az.cybernet.internship.dictionary.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DictionaryEntity {
    Integer id;

    String value;

    Boolean isActive;

    Integer limit;
}
