package az.cybernet.internship.dictionary.mapstruct;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.model.Dictionary;
import org.mapstruct.Mapper;

// Больше предпочитаю ручками, чем mapstruct, но кому это интересно? ¯\(°_o)/¯
@Mapper(componentModel = "spring")
public interface DictionaryEntryMapper {

    DictionaryResponse toDto(Dictionary entity);

    Dictionary toEntity(DictionaryRequest dto);
}

