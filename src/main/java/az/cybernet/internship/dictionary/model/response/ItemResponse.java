package az.cybernet.internship.dictionary.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {
    Long id;
    String value;
    String description;
    Boolean isActive;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
