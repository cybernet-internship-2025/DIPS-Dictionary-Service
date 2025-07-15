package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.entity.DictionaryCategory;
import az.cybernet.internship.dictionary.entity.DictionaryItem;
import az.cybernet.internship.dictionary.model.request.CategoryRequest;
import az.cybernet.internship.dictionary.model.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CategoryMapper {

    ItemMapper itemMapper;

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
                .items(category.getItems()
                        .stream()
                        .filter(DictionaryItem::getIsActive)
                        .map(itemMapper::buildItemResponse)
                        .toList())
                .build();
    }

    public void updateCategory(DictionaryCategory category, CategoryRequest request) {
        if (StringUtils.isNotEmpty(request.getName())) {
            category.setName(request.getName());
        }

        if (StringUtils.isNotEmpty(request.getDescription())) {
            category.setDescription(request.getDescription());
        }

        if (request.getId() != null) {
            category.setId(request.getId());
        }
    }
}
