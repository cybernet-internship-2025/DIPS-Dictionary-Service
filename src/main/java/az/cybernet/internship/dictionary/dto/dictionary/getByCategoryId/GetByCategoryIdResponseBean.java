package az.cybernet.internship.dictionary.dto.dictionary.getByCategoryId;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetByCategoryIdResponseBean {
    UUID id;
    String value;
    String description;
    String createdAt;
    UUID categoryId;
}
