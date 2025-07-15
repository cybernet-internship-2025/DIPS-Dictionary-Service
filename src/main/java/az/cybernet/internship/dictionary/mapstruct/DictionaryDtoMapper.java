package az.cybernet.internship.dictionary.mapstruct;

import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.model.Dictionary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DictionaryDtoMapper {
    Dictionary toEntity(DictionaryResponse dto);
    DictionaryResponse toDto(Dictionary entity);
}
