package az.cybernet.internship.dictionary.repository;

import az.cybernet.internship.dictionary.model.DictionaryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface DictionaryRepository {
    List<DictionaryEntity> getAll(@Param("id") String id, @Param("value") String value, @Param("isActive") Boolean isActive);

    DictionaryEntity getById(@Param("id") String id);

    void insert(DictionaryEntity entity);

    void update(DictionaryEntity entity);

    void softDelete(@Param("id") String id);

    void restore(@Param("id") String id);
}