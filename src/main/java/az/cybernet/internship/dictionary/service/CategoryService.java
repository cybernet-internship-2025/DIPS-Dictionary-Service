package az.cybernet.internship.dictionary.service;


import az.cybernet.internship.dictionary.error.ErrorCode;
import az.cybernet.internship.dictionary.error.NotFoundException;
import az.cybernet.internship.dictionary.mapper.CategoryMapper;
import az.cybernet.internship.dictionary.model.category.CategoryDto;
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

    public CategoryDto getCategoryWithItems(UUID categoryId) {
        return categoryMapper.getByIdWithItems(categoryId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND,"Category not found with id: " + categoryId));
    }
}

