package az.cybernet.internship.dictionary.mapper;
import az.cybernet.internship.dictionary.model.category.Category;
import az.cybernet.internship.dictionary.model.category.CategoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface CategoryMapper {

    List<CategoryDto> getAll();

    Optional<CategoryDto> getByIdWithItems(@Param("id") UUID id);
    Optional<Category>findByIdWithoutDictionaries(@Param("id") UUID id);

    void update(@Param("category") Category category);

    void delete(@Param("id") UUID id);
    void insert(Category category);
    void restore(@Param("id") UUID id);
    Optional<Category>findByIdAndDisabled(@Param("id") UUID id);
}

