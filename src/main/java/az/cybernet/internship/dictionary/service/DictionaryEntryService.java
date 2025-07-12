package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.request.DictionaryEntryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryEntryResponseDTO;
import az.cybernet.internship.dictionary.model.DictionaryEntry;

import java.util.List;
import java.util.UUID;

public interface DictionaryEntryService {
    List<DictionaryEntryResponseDTO> selectAll();

    DictionaryEntry selectById(UUID id);

    void insert(DictionaryEntryRequestDTO entryRequestDTO);

    void update(DictionaryEntryRequestDTO entryRequestDTO);

    void delete(UUID id);

    void restore(UUID id);
}
