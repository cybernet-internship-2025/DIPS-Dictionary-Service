package az.cybernet.internship.dictionary.repository;

import az.cybernet.internship.dictionary.model.DictionaryEntry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface DictionaryMapper {

    List<DictionaryEntry> findDictionaries(
            @Param("id") UUID id,
            @Param("value") String value,
            @Param("isActive") Boolean isActive,
            @Param("limit") Integer limit
    );


    Optional<DictionaryEntry> findById(@Param("id") UUID id);

    int insert(DictionaryEntry entry);

    int update(DictionaryEntry entry);

    int softDelete(@Param("id") UUID id, @Param("deletedAt") OffsetDateTime deletedAt, @Param("updatedAt") OffsetDateTime updatedAt);

    int restore(@Param("id") UUID id, @Param("updatedAt") OffsetDateTime updatedAt);

    Optional<DictionaryEntry> findByValue(@Param("value") String value);

    List<DictionaryEntry> findAll();
}