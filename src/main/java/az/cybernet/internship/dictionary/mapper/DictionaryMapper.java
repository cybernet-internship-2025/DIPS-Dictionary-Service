package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.model.dictionary.Dictionary;
import az.cybernet.internship.dictionary.model.dictionary.DictionaryRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface DictionaryMapper {

    void insert(Dictionary item);

    void update(Dictionary item);

    void softDelete(@Param("id") UUID id);

    List<Dictionary> findByCategoryId(@Param("categoryId") UUID categoryId);

    void restore(@Param("id") UUID id);

    Optional<Dictionary> findById(@Param("id") UUID id);

    List<Dictionary> filter(@Param("req") DictionaryRequest dictionaryRequest);

}

