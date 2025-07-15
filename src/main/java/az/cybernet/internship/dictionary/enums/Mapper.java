package az.cybernet.internship.dictionary.enums;

import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.model.DictionaryEntry;

import java.time.LocalDateTime;
import java.util.Dictionary;

public enum Mapper {
    DICTIONARY_MAPPER;
    public DictionaryResponse mapToResponse(DictionaryEntry entry) {
        return DictionaryResponse.builder()
                .id(entry.getId())
                .category(entry.getCategory())
                .value(entry.getValue())
                .description(entry.getDescription())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .build();


    }
}
