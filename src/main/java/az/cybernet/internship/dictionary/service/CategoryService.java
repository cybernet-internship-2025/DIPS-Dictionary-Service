package az.cybernet.internship.dictionary.service;


import az.cybernet.internship.dictionary.error.NotFoundException;
import az.cybernet.internship.dictionary.mapper.CategoryMapper;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.mapstruct.CategoryMapstruct;
import az.cybernet.internship.dictionary.mapstruct.DictionaryMapstruct;
import az.cybernet.internship.dictionary.model.category.Category;
import az.cybernet.internship.dictionary.model.category.CategoryDto;
import az.cybernet.internship.dictionary.model.category.CategoryWithItemsDto;
import az.cybernet.internship.dictionary.model.category.CategoryWithItemsResponse;
import az.cybernet.internship.dictionary.model.dictionary.Dictionary;
import az.cybernet.internship.dictionary.model.dictionary.DictionaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final DictionaryMapper dictionaryMapper;
    private final CategoryMapstruct categoryMapstruct;
    private final DictionaryMapstruct dictionaryMapstruct;

    public List<CategoryDto> getAllCategories() {
        return categoryMapper.getAll();
    }

    public CategoryWithItemsResponse getCategoryWithItems(UUID categoryId) {
        CategoryWithItemsDto category = categoryMapper.getByIdWithItems(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + categoryId));

        return categoryMapstruct.toWithItemsDto(category);
    }
}

