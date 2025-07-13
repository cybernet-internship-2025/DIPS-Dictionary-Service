package DictionaryDto;

import lombok.Data;

@Data
public class DictionaryResponse {
    private String id;
    private String category;
    private String value;
    private String description;
    private Boolean isActive;
}
