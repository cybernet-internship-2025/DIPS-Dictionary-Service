package DictionaryDto;

import lombok.Data;

@Data
public class DictionaryRequest {
    private String id;  // optional for creation
    private String value;
    private String description;
}
