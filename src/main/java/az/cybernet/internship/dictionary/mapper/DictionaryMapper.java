package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.model.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface DictionaryMapper {
    List<Dictionary> findAllActiveDictionaryWithLimit(@Param("value") String value,
                                                      @Param("isActive") Boolean isActive,
                                                      @Param("limit") int limit);

    void restore(@Param("id") UUID id);

    Dictionary findById(@Param("id") UUID id);
}
