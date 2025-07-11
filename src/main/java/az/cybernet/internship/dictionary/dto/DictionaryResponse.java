package az.cybernet.internship.dictionary.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class DictionaryResponse implements Serializable {
    private UUID id;
    private String category;
    private String value;
    private String description;

    public DictionaryResponse(UUID id, String category, String value, String description) {
        this.id = id;
        this.category = category;
        this.value = value;
        this.description = description;
    }

    public DictionaryResponse() {}
}
