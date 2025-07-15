package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.request.DictionaryCategoryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryCategoryResponseDTO;
import az.cybernet.internship.dictionary.model.DictionaryCategory;

import java.util.List;

public interface DictionaryCategoryService {
    List<DictionaryCategoryResponseDTO> selectAll();

    DictionaryCategoryResponseDTO insert(DictionaryCategoryRequestDTO categoryRequestDTO);

    DictionaryCategoryResponseDTO update(DictionaryCategoryRequestDTO categoryRequestDTO);

    void delete(String id);

    DictionaryCategory selectByName(String name);
}
