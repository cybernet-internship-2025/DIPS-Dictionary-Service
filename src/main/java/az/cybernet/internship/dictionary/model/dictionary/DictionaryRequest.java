package az.cybernet.internship.dictionary.model.dictionary;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DictionaryRequest {

    @NotBlank(message = "Value cannot be blank")
    private String value;

    private String description;

    @NotBlank(message = "CategoryId is required")
    private String categoryId;
}

