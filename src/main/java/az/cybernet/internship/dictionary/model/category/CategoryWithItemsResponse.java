package az.cybernet.internship.dictionary.model.category;


import az.cybernet.internship.dictionary.model.dictionary.DictionaryResponse;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CategoryWithItemsResponse {
    private UUID id;
    private String name;
    private String description;
    private List<DictionaryResponse> items;
}
