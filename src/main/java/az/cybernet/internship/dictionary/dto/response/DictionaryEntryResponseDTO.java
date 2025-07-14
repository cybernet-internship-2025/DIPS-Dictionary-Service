package az.cybernet.internship.dictionary.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DictionaryEntryResponseDTO {
    private UUID id;
    private String value;
    private String description;
    private String categoryName;
}
