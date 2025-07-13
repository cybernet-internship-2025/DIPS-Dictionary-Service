package az.cybernet.internship.dictionary.repository;

import az.cybernet.internship.dictionary.entity.DictionaryItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    void updateItem(@Param("id") Long id, @Param("item") DictionaryItem item);

    void saveItem(DictionaryItem item);

    void deleteItem(Long id);

    List<DictionaryItem> findAll(@Param("limit") Integer limit);

    Optional<DictionaryItem> findById(Long id);

    void restore(Long id);
}
