package az.cybernet.internship.dictionary.repository;

import az.cybernet.internship.dictionary.model.Dictionary;
import org.apache.ibatis.annotations.*;
import java.util.UUID;


import java.util.List;

@Mapper
public interface DictionaryRepository {

    @Select("""
        SELECT * FROM dictionary_entry
        WHERE (:id IS NULL OR id = #{id})
          AND (:value IS NULL OR LOWER(value) LIKE CONCAT('%', LOWER(#{value}), '%'))
          AND (:isActive IS NULL OR is_active = #{isActive})
        LIMIT #{limit}
    """)
    List<Dictionary> findAll(@Param("id") UUID id,
                             @Param("value") String value,
                             @Param("isActive") Boolean isActive,
                             @Param("limit") int limit);

    @Select("SELECT * FROM dictionary_entry WHERE id = #{id}")
    Dictionary findById(UUID id);

    @Insert("INSERT INTO dictionary_entry (id, value, description, is_active, deleted_at) VALUES (#{id}, #{value}, #{description}, #{isActive}, #{deletedAt})")
    void insert(Dictionary dictionary);

    @Update("UPDATE dictionary_entry SET value=#{value}, description=#{description}, is_active=#{isActive}, deleted_at=#{deletedAt} WHERE id=#{id}")
    void update(Dictionary dictionary);

    @Delete("DELETE FROM dictionary_entry WHERE id = #{id}")
    void delete(UUID id);


}