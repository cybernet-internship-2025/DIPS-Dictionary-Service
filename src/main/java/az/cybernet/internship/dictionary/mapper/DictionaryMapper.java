package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.model.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictionaryMapper {
    List<Dictionary> findAllActiveDictionaryWithLimit(@Param("value") String value,
                                                      @Param("isActive") Boolean isActive,
                                                      @Param("limit") int limit);
}
