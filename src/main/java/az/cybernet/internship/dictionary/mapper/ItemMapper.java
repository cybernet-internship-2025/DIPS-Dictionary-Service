package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.entity.DictionaryCategory;
import az.cybernet.internship.dictionary.entity.DictionaryItem;
import az.cybernet.internship.dictionary.model.request.ItemRequest;
import az.cybernet.internship.dictionary.model.response.ItemResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ItemMapper {

    public ItemResponse buildItemResponse(DictionaryItem item) {
        return ItemResponse.builder()
                .id(item.getId())
                .value(item.getValue())
                .description(item.getDescription())
                .isActive(item.getIsActive())
                .createdAt(item.getCreatedAt())
                .updatedAt(item.getUpdatedAt())
                .build();
    }

    public DictionaryItem buildDictionaryItem(ItemRequest request, DictionaryCategory category) {
        return DictionaryItem.builder()
                .value(request.getValue())
                .description(request.getDescription())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .category(category)
                .build();
    }

    public void updateItem(DictionaryItem item, ItemRequest request) {
        if (StringUtils.isNotEmpty(request.getValue())) {
            item.setValue(request.getValue());
        }

        if (StringUtils.isNotEmpty(request.getDescription())) {
            item.setDescription(request.getDescription());
        }

        if (request.getCategoryId() != null) {
            item.setId(request.getCategoryId());
        }
    }

}
