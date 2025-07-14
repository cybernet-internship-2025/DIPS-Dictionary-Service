package az.cybernet.internship.dictionary.converter;

import az.cybernet.internship.dictionary.dto.request.DictionaryEntryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryEntryResponseDTO;
import az.cybernet.internship.dictionary.model.DictionaryEntry;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface DictionaryEntryConverter {
    DictionaryEntry convert(DictionaryEntryRequestDTO dictionaryEntryRequestDTO);

    DictionaryEntryResponseDTO convert(DictionaryEntry dictionaryEntry);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntryFromDto(DictionaryEntryRequestDTO entryRequestDTO, @MappingTarget DictionaryEntry entity);
}
