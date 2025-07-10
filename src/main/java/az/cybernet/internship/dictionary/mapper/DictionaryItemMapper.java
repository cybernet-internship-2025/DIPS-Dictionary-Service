package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.dto.DictionaryItemRequest;
import az.cybernet.internship.dictionary.dto.DictionaryItemResponse;
import az.cybernet.internship.dictionary.model.DictionaryItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DictionaryItemMapper {
    DictionaryItem toEntity(DictionaryItemRequest request);
    DictionaryItemResponse toResponse(DictionaryItem item);
    List<DictionaryItemResponse> responseList(List<DictionaryItem> itemList);
}
