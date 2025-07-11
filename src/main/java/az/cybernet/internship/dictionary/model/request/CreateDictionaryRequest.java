package az.cybernet.internship.dictionary.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDictionaryRequest {
    private String value;
    private String description;
}
