package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.request.DictionaryEntryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryEntryResponseDTO;

import java.util.List;

public interface DictionaryEntryService {
    List<DictionaryEntryResponseDTO> selectAll();

    DictionaryEntryResponseDTO insert(DictionaryEntryRequestDTO entryRequestDTO);

    DictionaryEntryResponseDTO update(DictionaryEntryRequestDTO entryRequestDTO);

    void delete(String id);

    DictionaryEntryResponseDTO restore(String id);

    void deleteAllByName(String categoryID);
}
