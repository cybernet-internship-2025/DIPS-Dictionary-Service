package az.cybernet.internship.dictionary.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    Long id;
    @NotNull(message = "name is required")
    String name;
    String description;
}
