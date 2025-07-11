package az.cybernet.internship.dictionary.converter;

import az.cybernet.internship.dictionary.dto.request.DictionaryEntryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryEntryResponseDTO;
import az.cybernet.internship.dictionary.model.DictionaryEntry;

public interface DictionaryEntryConverter {
    DictionaryEntry convert(DictionaryEntryRequestDTO  dictionaryEntryRequestDTO);
    DictionaryEntryResponseDTO convert(DictionaryEntry dictionaryEntry);
}
