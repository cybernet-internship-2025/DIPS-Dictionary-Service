package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.entity.DictionaryCategory;
import az.cybernet.internship.dictionary.model.request.CategoryRequest;
import az.cybernet.internship.dictionary.model.response.CategoryResponse;

public enum CategoryMapper {
    CATEGORY_MAPPER;

    public DictionaryCategory buildDictionaryCategory(CategoryRequest request) {
        return DictionaryCategory.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .isActive(true)
                .build();
    }

    public CategoryResponse buildCategoryResponse(DictionaryCategory category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .isActive(category.getIsActive())
//                .items(
//                        category.getItems().stream()
//                                .map(ITEM_MAPPER::buildItemResponse)
//                                .collect(Collectors.toList())
//                )
                .build();
    }

    public void updateCategory(DictionaryCategory category, CategoryRequest request) {
        if (request.getName() != null && !request.getName().trim().isEmpty()) {
            category.setName(request.getName());
        }

        if (request.getDescription() != null && !request.getDescription().trim().isEmpty()) {
            category.setDescription(request.getDescription());
        }

        if (request.getId() != null) {
            category.setId(request.getId());
        }
    }
}
