package az.cybernet.internship.dictionary.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DictionaryRequest {
    String value;

    Boolean isActive;

    Integer limit;

    DictionaryRequest(){

    }
}
