package az.cybernet.internship.dictionary.mapstruct;

import az.cybernet.internship.dictionary.model.category.Category;
import az.cybernet.internship.dictionary.model.category.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapstruct {
    CategoryDto toDto(Category category);
    Category toModel(CategoryDto dto);
}
