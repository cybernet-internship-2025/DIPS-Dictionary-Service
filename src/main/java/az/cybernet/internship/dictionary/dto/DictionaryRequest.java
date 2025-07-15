package az.cybernet.internship.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryRequest {
    private UUID id;
    private String category;
    private String value;
    private String description;
}