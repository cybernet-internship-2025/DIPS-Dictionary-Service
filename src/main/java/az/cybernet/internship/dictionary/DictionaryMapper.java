package az.cybernet.internship.dictionary;

import java.util.List;

public interface DictionaryMapper {
    List<DictionaryEntry> list(Long id, String value, Boolean is_active, int limit);

    void insert(DictionaryEntry entry);

    void update(DictionaryEntry entry);

    void softDelete(Long id);

    void restore(Long id);

    Boolean isActive(Long id);

    DictionaryEntry findById(Long id);
}

