package az.cybernet.internship.dictionary.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DictionaryCategoryResponseDTO {
    private UUID id;
    private String name;
}
