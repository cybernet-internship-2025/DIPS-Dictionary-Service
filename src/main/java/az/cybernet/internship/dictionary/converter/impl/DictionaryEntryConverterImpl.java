package az.cybernet.internship.dictionary.converter.impl;

import az.cybernet.internship.dictionary.converter.DictionaryEntryConverter;
import az.cybernet.internship.dictionary.dto.request.DictionaryEntryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryEntryResponseDTO;
import az.cybernet.internship.dictionary.model.DictionaryEntry;
import org.springframework.stereotype.Service;

@Service
public class DictionaryEntryConverterImpl implements DictionaryEntryConverter {
    @Override
    public DictionaryEntry convert(DictionaryEntryRequestDTO dictionaryEntryRequestDTO) {
        return DictionaryEntry.builder()
                .id(dictionaryEntryRequestDTO.getId())
                .value(dictionaryEntryRequestDTO.getValue())
                .description(dictionaryEntryRequestDTO.getDescription())
                .categoryName(dictionaryEntryRequestDTO.getCategoryName())
                .build();
    }

    @Override
    public DictionaryEntryResponseDTO convert(DictionaryEntry dictionaryEntry) {
        return DictionaryEntryResponseDTO.builder()
                .id(dictionaryEntry.getId())
                .value(dictionaryEntry.getValue())
                .description(dictionaryEntry.getDescription())
                .categoryName(dictionaryEntry.getCategoryName())
                .build();
    }
}
