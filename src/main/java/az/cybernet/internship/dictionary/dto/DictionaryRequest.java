package az.cybernet.internship.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryRequest {
    private Long id;
    private String category;
    private String value;
    private String description;


}
