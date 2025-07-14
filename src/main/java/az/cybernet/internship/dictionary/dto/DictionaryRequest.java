package az.cybernet.internship.dictionary.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DictionaryRequest {
    private UUID id;
    private String value;
    private String description;
    private Boolean isActive;
}
