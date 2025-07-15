package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.dto.dictionary.filterDictionary.FilterDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.updateDictionary.UpdateDictionaryRequestBean;
import az.cybernet.internship.dictionary.model.dictionary.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface DictionaryMapper {

    void insert(Dictionary item);

    void update(@Param("dictionary") Dictionary dictionary);

    void softDelete(@Param("id") UUID id);

    List<Dictionary> findByCategoryId(@Param("categoryId") UUID categoryId);

    void restore(@Param("id") UUID id);

    Optional<Dictionary> findById(@Param("id") UUID id);
    Optional<Dictionary> findByIdNotActive(@Param("id") UUID id);

    List<Dictionary> filter(@Param("req") FilterDictionaryRequestBean request);

    void changeActivityStatusByCategoryId(@Param("categoryId") UUID categoryId, @Param("activityStatus") Boolean activityStatus);

}

