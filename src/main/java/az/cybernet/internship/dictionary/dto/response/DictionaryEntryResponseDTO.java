package az.cybernet.internship.dictionary.dto.response;

import lombok.Data;

@Data
public class DictionaryEntryResponseDTO {
    private String value;
    private String description;
    private String categoryName;
}
