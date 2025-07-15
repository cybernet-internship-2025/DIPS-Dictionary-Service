package az.cybernet.internship.dictionary.mapper; // Corrected package name

import az.cybernet.internship.dictionary.model.DictionaryEntry;
import az.cybernet.internship.dictionary.model.dto.DictionaryCreateUpdateDto;
import az.cybernet.internship.dictionary.model.dto.DictionaryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DictionaryServiceMapper {

    DictionaryResponseDto toDto(DictionaryEntry dictionaryEntry);

    List<DictionaryResponseDto> toDtoList(List<DictionaryEntry> dictionaryEntries);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    DictionaryEntry toEntity(DictionaryCreateUpdateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    void updateEntityFromDto(DictionaryCreateUpdateDto dto, @MappingTarget DictionaryEntry entity);
}