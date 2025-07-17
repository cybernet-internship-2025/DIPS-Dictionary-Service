package az.cybernet.internship.dictionary.enums;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.model.DictionaryEntry;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Dictionary;

public enum Mapper {
    DICTIONARY_MAPPER;
//    public DictionaryResponse mapToResponse(DictionaryEntry entry) {
//        return DictionaryResponse.builder()
//                .id(entry.getId())
//                .category(entry.getCategory())
//                .value(entry.getValue())
//                .description(entry.getDescription())
//                .isActive(true)
//                .createdAt(LocalDateTime.now())
//                .updatedAt(null)
//                .build();
//    }
//
    public void updateResponse(DictionaryEntry dictionaryEntry, DictionaryRequest dictionaryRequest) {
        dictionaryEntry.setCategory(dictionaryRequest.getCategory());
        if (StringUtils.isNotEmpty(dictionaryRequest.getDescription())) {
            dictionaryEntry.setDescription(dictionaryRequest.getDescription());
        }
        dictionaryEntry.setValue(dictionaryRequest.getValue());
        dictionaryEntry.setCreatedAt(LocalDateTime.now());
        dictionaryEntry.setUpdatedAt(LocalDateTime.now());
        dictionaryEntry.setIsActive(true);
    }
//
//    public DictionaryEntry buildEntry(DictionaryRequest entry) {
//        return DictionaryEntry.builder()
//                .id(entry.getId())
//                .category(entry.getCategory())
//                .value(entry.getValue())
//                .description(entry.getDescription())
//                .isActive(true)
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .build();
//    }

}
