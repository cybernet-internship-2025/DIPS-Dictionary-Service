package az.cybernet.internship.dictionary.repository;

import az.cybernet.internship.dictionary.entity.DictionaryCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    void upsert(DictionaryCategory category);

    DictionaryCategory findById(Long id);

    void deleteCategory(Long id);

}
