package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.entity.Dictionary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper
public interface DictionaryMapper {

    List<Dictionary> findByFilters(UUID id, String value, Boolean isActive, Integer limit);
}
