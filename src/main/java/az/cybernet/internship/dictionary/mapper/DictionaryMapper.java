package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.entity.DictionaryEntity;
import az.cybernet.internship.dictionary.service.dto.DictionaryRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictionaryMapper {

   DictionaryEntity findById(Integer id);

    List<DictionaryEntity> findAll(Integer id, String value, Boolean isActive, Integer limit);

    Integer insert(DictionaryEntity request);

   Integer update(Integer id, DictionaryRequest request);

    Integer delete(Integer id);
}
