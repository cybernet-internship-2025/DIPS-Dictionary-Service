package az.cybernet.internship.dictionary.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DictionaryResponse {
    private Long id;
    private String value;
    private String description;
    private LocalDateTime deletedAt;
}
