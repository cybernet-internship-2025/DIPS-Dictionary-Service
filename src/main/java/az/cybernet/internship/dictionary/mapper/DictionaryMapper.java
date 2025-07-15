package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.model.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface DictionaryMapper {
    // Balash's commit
    Dictionary findById(@Param("id") UUID id);

    List<Dictionary> findAllActiveDictionaryWithLimit(@Param("value") String value, @Param("isActive") Boolean isActive, @Param("limit") int limit);

    void restore(@Param("id") UUID id);

    // Goychek's commit


    // Без комментариев, но с комментарием (。_。)
    void insert(Dictionary entry);

    int update(Dictionary entry);
}
