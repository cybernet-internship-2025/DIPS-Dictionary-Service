package az.cybernet.internship.dictionary.repository;

import az.cybernet.internship.dictionary.model.DictionaryEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

import java.util.UUID;
@Mapper

public interface DictionaryRepository {

    DictionaryEntity getById(@Param("id") UUID id);

    void insert(DictionaryEntity entity);

    void update(DictionaryEntity entity);

    void softDelete(@Param("id") UUID id);

    void restore(@Param("id") UUID id);
}
