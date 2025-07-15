package az.cybernet.internship.dictionary.model.category;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryDto {
    private UUID id;
    private String name;
    private String description;
}

