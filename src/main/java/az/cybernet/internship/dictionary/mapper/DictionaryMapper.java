package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.model.DictionaryEntry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictionaryMapper {
    // Balash's commit
    DictionaryEntry findById(@Param("id") String id);

    List<DictionaryEntry> findAllActiveDictionaryWithLimit(@Param("value") String value, @Param("isActive") Boolean isActive, @Param("limit") int limit);

    void restore(@Param("id") String id);

    // Goychek's commit


    // Без комментариев, но с комментарием (。_。)
    void insert(DictionaryEntry entry);

    int update(DictionaryEntry entry);
}
