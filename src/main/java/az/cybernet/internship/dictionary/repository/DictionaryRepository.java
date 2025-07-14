package az.cybernet.internship.dictionary.repository;

import az.cybernet.internship.dictionary.model.DictionaryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Mapper
@Repository
public interface DictionaryRepository {
    List<DictionaryEntity> getAll(@Param("id") UUID id,
                                  @Param("value") String value,
                                  @Param("isActive") Boolean isActive);

    DictionaryEntity getById(@Param("id") UUID id);

    void insert(DictionaryEntity entity);

    void update(DictionaryEntity entity);

    void softDelete(@Param("id") UUID id);

    void restore(@Param("id") UUID id);
}
