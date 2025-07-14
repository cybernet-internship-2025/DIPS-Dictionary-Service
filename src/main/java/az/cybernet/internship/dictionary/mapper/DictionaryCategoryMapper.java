package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.model.DictionaryCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface DictionaryCategoryMapper {

    @Select("SELECT * FROM dictionary_category")
    List<DictionaryCategory> getAll();

    @Select("SELECT * FROM dictionary_category WHERE id = #{id, jdbcType=OTHER}")
    DictionaryCategory getById(@Param("id") UUID id);

    @Insert("INSERT INTO dictionary_category(id, name) VALUES(#{id, jdbcType=OTHER}, #{name})")
    void insert(DictionaryCategory dictionaryCategory);

    @Update("UPDATE dictionary_category SET name = #{name} WHERE id = #{id, jdbcType=OTHER}")
    void update(DictionaryCategory dictionaryCategory);

    @Delete("DELETE FROM dictionary_category WHERE id = #{id, jdbcType=OTHER}")
    void delete(@Param("id") UUID id);
}
