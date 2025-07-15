package az.cybernet.internship.dictionary.mapper;

import az.cybernet.internship.dictionary.dao.entity.DictionaryEntity;
import az.cybernet.internship.dictionary.model.request.CreateDictionaryRequest;
import az.cybernet.internship.dictionary.model.response.DictionaryResponse;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface DictionaryMapper {
    DictionaryResponse toResponse(DictionaryEntity entity);
    DictionaryEntity toEntity(CreateDictionaryRequest request);

}
