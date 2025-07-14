package az.cybernet.internship.dictionary.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DictionaryCategoryRequestDTO {
    private UUID id;
    private String name;
}
