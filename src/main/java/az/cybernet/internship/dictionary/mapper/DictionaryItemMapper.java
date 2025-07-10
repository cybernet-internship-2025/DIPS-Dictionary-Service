package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.model.DictionaryItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictionaryItemMapper {
    List<DictionaryItem> findAllActiveWithLimit(@Param("limit") int limit);
}
