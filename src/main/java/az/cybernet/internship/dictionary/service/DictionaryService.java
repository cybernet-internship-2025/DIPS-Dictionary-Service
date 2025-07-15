package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;

import java.util.List;
import java.util.UUID;

public interface DictionaryService {
    List<DictionaryResponse> getAllActiveDictionaryWithLimit(String value, Boolean isActive, int limit);
    void restoreDictionary(UUID id);
    void saveOrUpdate(DictionaryRequest request);
    void softDelete(UUID id);
}