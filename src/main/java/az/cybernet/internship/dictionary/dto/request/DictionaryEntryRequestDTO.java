package az.cybernet.internship.dictionary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DictionaryEntryRequestDTO implements Serializable {
    private String id;
    @NotBlank(message = "Value must not be blank")
    private String value;
    private String description;
    @NotBlank(message = "Category name must not be blank")
    private String categoryName;
}
