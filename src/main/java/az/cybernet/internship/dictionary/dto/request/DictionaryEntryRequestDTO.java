package az.cybernet.internship.dictionary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DictionaryEntryRequestDTO {
    private String id;
    @NotBlank(message = "Value must not be blank")
    private String value;
    private String description;
    @NotBlank(message = "Category name must not be blank")
    private String categoryName;
}
