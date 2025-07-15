package az.cybernet.internship.dictionary.service.abstraction;

import az.cybernet.internship.dictionary.model.request.ItemRequest;
import az.cybernet.internship.dictionary.model.response.ItemResponse;

import java.util.List;

public interface ItemService {

    void updateItem(Long id, ItemRequest request);

    ItemResponse findById(Long id);

    void restoreItem(Long id);

    void deleteItemById(Long id);

    List<ItemResponse> findAllActiveItems(Integer limit);

    void saveItem(ItemRequest request);
}
