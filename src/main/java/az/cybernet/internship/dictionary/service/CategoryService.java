package az.cybernet.internship.dictionary.service;


import az.cybernet.internship.dictionary.error.ErrorCode;
import az.cybernet.internship.dictionary.error.NotFoundException;
import az.cybernet.internship.dictionary.mapper.CategoryMapper;
import az.cybernet.internship.dictionary.model.category.CategoryDto;
import az.cybernet.internship.dictionary.model.category.CategoryWithItemsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getAllCategories() {
        return categoryMapper.getAll();
    }

    public CategoryWithItemsDto getCategoryWithItems(UUID categoryId) {
        return categoryMapper.getByIdWithItems(categoryId)
                .orElseThrow(NotFoundException::new);
    }
}

