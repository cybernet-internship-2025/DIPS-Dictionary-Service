package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;

import java.util.List;

public interface DictionaryService {
    List<DictionaryResponse> getAll(String id, String value, Boolean isActive);
    void createOrUpdate(String id, DictionaryRequest request);
    void softDelete(String id);
    void restore(String id);
}