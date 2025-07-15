package az.cybernet.internship.dictionary.mapstruct;

import az.cybernet.internship.dictionary.model.category.Category;
import az.cybernet.internship.dictionary.model.category.CategoryDto;
import az.cybernet.internship.dictionary.model.category.CategoryWithItemsDto;
import az.cybernet.internship.dictionary.model.category.CategoryWithItemsResponse;
import az.cybernet.internship.dictionary.model.dictionary.DictionaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapstruct {
    CategoryDto toDto(Category category);
    Category toModel(CategoryDto dto);

    CategoryWithItemsResponse toWithItemsDto(CategoryWithItemsDto category);
}

