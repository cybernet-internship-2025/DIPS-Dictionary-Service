package az.cybernet.internship.dictionary.repository;

import az.cybernet.internship.dictionary.entity.DictionaryCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CategoryMapper {

    void saveCategory(DictionaryCategory category);

    void updateCategory(DictionaryCategory category);

    Optional<DictionaryCategory> findById(Long id);

    List<DictionaryCategory> findAll(Integer limit);

    void deleteCategory(Long id);

    void restoreCategory(Long id);
}
