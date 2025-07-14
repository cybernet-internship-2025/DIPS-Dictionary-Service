package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.model.DictionaryCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DictionaryCategoryMapper {
    @Select("SELECT * FROM dictionary_category")
    List<DictionaryCategory> selectAll();

    @Select("SELECT * FROM dictionary_category WHERE id = #{id, jdbcType=VARCHAR}")
    DictionaryCategory selectById(String id);

    @Select("SELECT * FROM dictionary_category WHERE name=#{name}")
    DictionaryCategory selectByName(String name);

    @Insert("INSERT INTO dictionary_category(id, name) VALUES(#{id, jdbcType=VARCHAR}, #{name})")
    void insert(DictionaryCategory dictionaryCategory);

    @Update("UPDATE dictionary_category SET name = #{name} WHERE id = #{id, jdbcType=VARCHAR}")
    void update(DictionaryCategory dictionaryCategory);

    @Delete("DELETE FROM dictionary_category WHERE id = #{id, jdbcType=VARCHAR}")
    void delete(String id);
}
