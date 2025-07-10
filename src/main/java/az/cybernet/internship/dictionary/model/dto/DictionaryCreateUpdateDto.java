package az.cybernet.internship.dictionary.model.dto; // Corrected package name

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryCreateUpdateDto {
    @NotBlank(message = "Value cannot be empty")
    private String value;
    private String description;
    private Boolean isActive; // Optional for creation/update
}