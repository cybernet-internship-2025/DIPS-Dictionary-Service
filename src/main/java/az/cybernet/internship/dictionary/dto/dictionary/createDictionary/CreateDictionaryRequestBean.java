package az.cybernet.internship.dictionary.dto.dictionary.createDictionary;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateDictionaryRequestBean {
    private UUID id;

    @NotBlank(message = "Value cannot be blank!")
    private String value;
    @NotBlank(message = "Description cannot be blank!")
    private String description;

    @NotNull(message = "CategoryId is required!")
    private UUID categoryId;
}
