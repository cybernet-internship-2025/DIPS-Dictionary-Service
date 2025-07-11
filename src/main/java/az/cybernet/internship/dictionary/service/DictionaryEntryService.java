package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.model.DictionaryEntry;

import java.util.List;
import java.util.UUID;

public interface DictionaryEntryService {
    List<DictionaryEntry> selectAll();

    DictionaryEntry selectById(UUID id);

    void insert(DictionaryEntry entry);

    void update(DictionaryEntry entry);

    void delete(UUID id);

    void restore(UUID id);
}
