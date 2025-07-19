package az.cybernet.internship.dictionary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DictionaryCategoryRequestDTO implements Serializable {
    private String id;
    private String name;
}
