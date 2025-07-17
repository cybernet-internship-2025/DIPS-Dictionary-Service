package az.cybernet.internship.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DictionaryRequest {
    private Long id;
    private String category;
    private String value;
    private String description;
}
