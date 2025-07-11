package az.cybernet.internship.dictionary;

import java.util.List;

public interface DictionaryMapper {
    List<DictionaryEntity> list(Long id, String value, Boolean is_active, Integer limit);

    void insert(DictionaryEntity entry);

    int update(DictionaryEntity entry);

    void softDelete(Long id);

    void restore(Long id);

    Boolean isActive(Long id);
}

