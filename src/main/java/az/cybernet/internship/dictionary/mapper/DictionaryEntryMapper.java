package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.model.DictionaryEntry;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DictionaryEntryMapper {
    @Select("SELECT * FROM dictionary_entry")
    List<DictionaryEntry> selectAll();

    @Select("SELECT * FROM dictionary_entry WHERE id=#{id, jdbcType=VARCHAR}")
    DictionaryEntry selectById(String id);

    @Insert("""
            INSERT INTO dictionary_entry(id, value, description, is_active, deleted_at, category_id)
            VALUES(#{id, jdbcType=VARCHAR}, #{value}, #{description}, #{isActive}, #{deletedAt}, #{categoryID, jdbcType=VARCHAR})
            """)
    void insert(DictionaryEntry entry);

    @Update("""
            UPDATE dictionary_entry
            SET value=#{value}, description=#{description}, is_active=#{isActive},
                deleted_at=#{deletedAt}, category_id=#{categoryID, jdbcType=VARCHAR}
            WHERE id=#{id, jdbcType=VARCHAR}
            """)
    void update(DictionaryEntry entry);
}
