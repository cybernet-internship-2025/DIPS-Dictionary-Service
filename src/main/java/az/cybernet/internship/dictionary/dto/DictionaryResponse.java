package az.cybernet.internship.dictionary.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DictionaryResponse {


    private UUID id;
    private String value;
    private String description;
    private Boolean isActive;
}
