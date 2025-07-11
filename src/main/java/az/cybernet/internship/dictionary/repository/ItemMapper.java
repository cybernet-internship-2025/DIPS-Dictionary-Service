package az.cybernet.internship.dictionary.repository;

import az.cybernet.internship.dictionary.entity.DictionaryItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ItemMapper {

    void updateItem(@Param("id") Long id, @Param("item") DictionaryItem item);

    Optional<DictionaryItem> findById(Long id);

    void restore(Long id);

}
