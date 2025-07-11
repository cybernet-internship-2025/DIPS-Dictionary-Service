package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.DictionaryResponse;

import java.util.List;

public interface DictionaryService {
    List<DictionaryResponse> getAllActiveDictionaryWithLimit(String value, Boolean isActive, int limit);
}
