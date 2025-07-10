package az.cybernet.internship.dictionary.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DictionaryItemResponse {
    private String category;
    private String value;
    private String description;
}
