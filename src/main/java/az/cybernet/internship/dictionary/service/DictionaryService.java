package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.model.Dictionary;
import java.util.UUID; // ✅ doğru olan budur



import java.util.List;
import java.util.Optional;

public interface DictionaryService {
    List<Dictionary> findAll(UUID id, String value, Boolean isActive, Integer limit);
    Dictionary save(Dictionary dictionary);
    void delete(UUID id);
    void restore(UUID id);
    Optional<Dictionary> findById(UUID id);
}