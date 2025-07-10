package az.cybernet.internship.dictionary;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Repository {
    List<Entry> list(Long id, String value, Boolean is_active, int limit);

    int insert(Entry entry);

    int update(Entry entry);

    int softDelete(Long id);

    int restore(Long id);

    Boolean isActive(Long id);

    Entry findById(Long id);
}

