package az.cybernet.internship.dictionary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DictionaryCategoryResponseDTO implements Serializable {
    private String id;
    private String name;
}
