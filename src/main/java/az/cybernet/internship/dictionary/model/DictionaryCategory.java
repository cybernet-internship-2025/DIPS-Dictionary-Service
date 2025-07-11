package az.cybernet.internship.dictionary.model;

import lombok.Data;

import java.util.UUID;

@Data
public class DictionaryCategory {
    private UUID id;
    private String name;
}
