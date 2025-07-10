package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.DictionaryItemRequest;
import az.cybernet.internship.dictionary.dto.DictionaryItemResponse;

import java.util.List;

public interface DictionaryItemService {
    List<DictionaryItemResponse> getAllActiveDictionary();
    DictionaryItemResponse createOrUpdate(DictionaryItemRequest request);
    void delete(Long id);
    DictionaryItemResponse restore(Long id);
}
