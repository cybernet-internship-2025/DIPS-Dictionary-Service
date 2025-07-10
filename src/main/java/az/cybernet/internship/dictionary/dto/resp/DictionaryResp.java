package az.cybernet.internship.dictionary.dto.resp;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DictionaryResp {
    String category;
    String value;
    String description;
    Boolean isActive;
    LocalDateTime createDate;
    LocalDateTime updateDate;
}
