package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.exception.DictionaryNotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.model.Dictionary;
import az.cybernet.internship.dictionary.service.DictionaryService;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryMapper mapper;

    public DictionaryServiceImpl(DictionaryMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<DictionaryResponse> getAllActiveDictionaryWithLimit(String value, Boolean isActive,int limit) {
        List<Dictionary> items = mapper.findAllActiveDictionaryWithLimit(value, isActive, limit);

        if (items.isEmpty()) throw new DictionaryNotFoundException("Dictionay not found");

        return items.stream()
                .map(item -> new DictionaryResponse(
                        item.getId(),
                        item.getCategory(),
                        item.getValue(),
                        item.getDescription()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DictionaryResponse restoreDictionary(UUID uuid) {
        mapper.restore(uuid);

        Dictionary dictionary = mapper.findById(uuid);

        DictionaryResponse response = new DictionaryResponse();
        response.setId(dictionary.getId());
        response.setCategory(dictionary.getCategory());
        response.setValue(dictionary.getValue());
        response.setDescription(dictionary.getDescription());

        return response;
    }

    @Override
    @Transactional
    public DictionaryResponse updateOrInsert(DictionaryRequest request) {
        Dictionary dictionary = mapper.findById(request.getId());
        if (dictionary != null) {
            dictionary.setCategory(request.getCategory());
            dictionary.setValue(request.getValue());
            dictionary.setDescription(request.getDescription());
            dictionary.setIsActive(request.getIsActive());
            dictionary.setDeletedAt(request.getDeletedAt());
            mapper.update(dictionary);
        } else {
            dictionary = new Dictionary();
            dictionary.setId(request.getId());
            dictionary.setCategory(request.getCategory());
            dictionary.setValue(request.getValue());
            dictionary.setDescription(request.getDescription());
            dictionary.setIsActive(request.getIsActive());
            dictionary.setDeletedAt(request.getDeletedAt());
            mapper.insert(dictionary);
        }

        DictionaryResponse response = new DictionaryResponse();
        response.setId(dictionary.getId());
        response.setCategory(dictionary.getCategory());
        response.setValue(dictionary.getValue());
        response.setDescription(dictionary.getDescription());

        return response;
    }

    @Override
    public DictionaryResponse softDelete(UUID uuid) {
        Dictionary dictionary = mapper.findById(uuid);
        if (dictionary == null) throw new DictionaryNotFoundException("Dictionay not found");

        dictionary.setIsActive(false);
        dictionary.setDeletedAt(LocalDateTime.now());

        mapper.updateForSoftDelete(dictionary.getId());

        DictionaryResponse response = new DictionaryResponse();
        response.setId(dictionary.getId());
        response.setCategory(dictionary.getCategory());
        response.setValue(dictionary.getValue());
        response.setDescription(dictionary.getDescription());

        return response;
    }
}
