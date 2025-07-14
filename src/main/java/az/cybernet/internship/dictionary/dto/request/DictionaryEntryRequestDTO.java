package az.cybernet.internship.dictionary.dto.request;

import lombok.Data;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

@Data
public class DictionaryEntryRequestDTO {
    private UUID id;
    @NotBlank(message = "Value must not be blank")
    private String value;
    private String description;
    private String categoryName;
}
