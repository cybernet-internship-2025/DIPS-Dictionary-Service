package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.DictionaryResponse;

import java.util.List;
import java.util.UUID;

public interface DictionaryService {
    // Balash's commit
    List<DictionaryResponse> getAllActiveDictionaryWithLimit(String value, Boolean isActive, int limit);

    DictionaryResponse restoreDictionary(UUID uuid);

    // Goychek commit
    void deleteDictionary(UUID uuid);

    // Huseyn commit
}
