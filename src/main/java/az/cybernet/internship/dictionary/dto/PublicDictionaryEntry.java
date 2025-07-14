package az.cybernet.internship.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicDictionaryEntry {
    private String id;
    private String category;
    private String value;
    private String description;
}
