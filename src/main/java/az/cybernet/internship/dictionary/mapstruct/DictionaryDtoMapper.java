package az.cybernet.internship.dictionary.mapstruct;

import az.cybernet.internship.dictionary.entity.DictionaryEntity;
import az.cybernet.internship.dictionary.service.dto.DictionaryRequest;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DictionaryDtoMapper {

    DictionaryEntity toEntity(DictionaryRequest request);
    DictionaryRequest toRequest(DictionaryEntity entity);
}
