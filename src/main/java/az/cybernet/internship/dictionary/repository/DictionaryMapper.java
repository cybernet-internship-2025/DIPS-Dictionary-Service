package az.cybernet.internship.dictionary.repository; // <-- CORRECTED PACKAGE NAME

import az.cybernet.internship.dictionary.model.DictionaryEntry; // Assuming model package is az.cybernet.internship.dictionary.model
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface DictionaryMapper {

    // Note: Assuming DictionarySqlProvider will also be in this 'repository' package
    @SelectProvider(type = DictionarySqlProvider.class, method = "findDictionaries")
    List<DictionaryEntry> findDictionaries(
            @Param("id") UUID id,
            @Param("value") String value,
            @Param("isActive") Boolean isActive,
            @Param("limit") Integer limit
    );

    @Select("SELECT * FROM dictionaries WHERE id = #{id}")
    Optional<DictionaryEntry> findById(@Param("id") UUID id);

    @Insert("INSERT INTO dictionaries (id, value, description, is_active, created_at, updated_at) " +
            "VALUES (#{id}, #{value}, #{description}, #{isActive}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = false) // UUIDs are generated in Java
    int insert(DictionaryEntry entry);

    @Update("UPDATE dictionaries SET " +
            "value = #{value}, " +
            "description = #{description}, " +
            "is_active = #{isActive}, " +
            "updated_at = #{updatedAt} " +
            "WHERE id = #{id}")
    int update(DictionaryEntry entry);

    @Update("UPDATE dictionaries SET " +
            "is_active = FALSE, " +
            "deleted_at = #{deletedAt}, " +
            "updated_at = #{updatedAt} " +
            "WHERE id = #{id}")
    int softDelete(@Param("id") UUID id, @Param("deletedAt") java.time.OffsetDateTime deletedAt, @Param("updatedAt") java.time.OffsetDateTime updatedAt);

    @Update("UPDATE dictionaries SET " +
            "is_active = TRUE, " +
            "deleted_at = NULL, " +
            "updated_at = #{updatedAt} " +
            "WHERE id = #{id}")
    int restore(@Param("id") UUID id, @Param("updatedAt") java.time.OffsetDateTime updatedAt);

    @Select("SELECT * FROM dictionaries WHERE value = #{value} LIMIT 1")
    Optional<DictionaryEntry> findByValue(@Param("value") String value);
}