package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.model.DictionaryEntry;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface DictionaryEntryMapper {
    @Select("SELECT * FROM dictionary_entry")
    List<DictionaryEntry> selectAll();

    @Select("SELECT * FROM dictionary_entry WHERE id=#{id}")
    DictionaryEntry selectById(UUID id);

    @Insert("""
        INSERT INTO dictionary_entry(id, value, description, is_active, deleted_at, category_id)
        VALUES(#{id}, #{value}, #{description}, #{isActive}, #{deletedAt}, #{categoryId})
        """)
    void insert(DictionaryEntry entry);

    @Update("""
        UPDATE dictionary_entry
        SET value = #{value}, description = #{description}, is_active = #{isActive},
            deleted_at = #{deletedAt}, category_id = #{categoryId}
        WHERE id = #{id}
        """)
    void update(DictionaryEntry entry);

    @Delete("DELETE FROM dictionary_entry WHERE id=#{id}")
    void delete(UUID id);
}
