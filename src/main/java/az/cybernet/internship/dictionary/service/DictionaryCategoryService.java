package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.request.DictionaryCategoryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryCategoryResponseDTO;

import java.util.List;
import java.util.UUID;

public interface DictionaryCategoryService {
    List<DictionaryCategoryResponseDTO> selectAll();

    DictionaryCategoryResponseDTO insert(DictionaryCategoryRequestDTO categoryRequestDTO);

    DictionaryCategoryResponseDTO update(DictionaryCategoryRequestDTO categoryRequestDTO);

    void delete(UUID id);
}
