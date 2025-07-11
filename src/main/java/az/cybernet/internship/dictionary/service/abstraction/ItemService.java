package az.cybernet.internship.dictionary.service.abstraction;

import az.cybernet.internship.dictionary.model.request.ItemRequest;
import az.cybernet.internship.dictionary.model.response.ItemResponse;

public interface ItemService {

    void updateItem(Long id, ItemRequest request);

    ItemResponse findById(Long id);

    void restore(Long id);

}
