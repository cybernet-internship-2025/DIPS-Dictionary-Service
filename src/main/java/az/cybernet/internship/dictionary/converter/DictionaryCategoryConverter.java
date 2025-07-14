package az.cybernet.internship.dictionary.converter;

import az.cybernet.internship.dictionary.dto.request.DictionaryCategoryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryCategoryResponseDTO;
import az.cybernet.internship.dictionary.model.DictionaryCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DictionaryCategoryConverter {
    DictionaryCategory convert(DictionaryCategoryRequestDTO dictionaryCategoryRequestDTO);

    DictionaryCategoryResponseDTO convert(DictionaryCategory dictionaryCategory);
}
