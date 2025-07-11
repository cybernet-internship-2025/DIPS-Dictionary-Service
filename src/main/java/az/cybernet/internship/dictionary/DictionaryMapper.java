package az.cybernet.internship.dictionary;

import java.util.List;

public interface DictionaryMapper {
    List<DictionaryEntry> list(Long id, String value, Boolean isActive, Integer limit);

    void insert(DictionaryEntry entry);

    int update(DictionaryEntry entry);

    void softDelete(Long id);

    void restore(Long id);

    Boolean isActive(Long id);
}

