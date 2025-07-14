package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.request.DictionaryEntryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryEntryResponseDTO;

import java.util.List;
import java.util.UUID;

public interface DictionaryEntryService {
    List<DictionaryEntryResponseDTO> selectAll();

    DictionaryEntryResponseDTO insert(DictionaryEntryRequestDTO entryRequestDTO);

    DictionaryEntryResponseDTO update(DictionaryEntryRequestDTO entryRequestDTO);

    void delete(UUID id);

    DictionaryEntryResponseDTO restore(UUID id);
}
