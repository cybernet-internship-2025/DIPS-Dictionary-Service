package az.cybernet.internship.dictionary.mapstruct;

import az.cybernet.internship.dictionary.dto.PublicDictionaryEntry;
import az.cybernet.internship.dictionary.model.DictionaryEntry;
import org.mapstruct.Mapper;

// Больше предпочитаю ручками, чем mapstruct, но кому это интересно? ¯\(°_o)/¯
@Mapper(componentModel = "spring")
public interface DictionaryEntryMapper {

    PublicDictionaryEntry toDto(DictionaryEntry entity);

    DictionaryEntry toEntity(PublicDictionaryEntry dto);
}

