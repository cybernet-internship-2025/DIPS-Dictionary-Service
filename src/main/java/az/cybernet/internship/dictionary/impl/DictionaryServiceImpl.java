package az.cybernet.internship.dictionary.impl;

import az.cybernet.internship.dictionary.dto.*;
import az.cybernet.internship.dictionary.model.*;
import az.cybernet.internship.dictionary.repository.*;
import az.cybernet.internship.dictionary.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {
    private final DictionaryRepository repository;

    @Override
    public List<DictionaryResponse> getAll(String id, String value, Boolean isActive) {
        return repository.getAll(id, value, isActive).stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public void createOrUpdate(String id, DictionaryRequest request) {
        DictionaryEntity entity = repository.getById(id);
        if (entity == null) {
            entity = new DictionaryEntity();
            entity.setId(UUID.fromString(id != null ? id : UUID.randomUUID().toString()));
            entity.setIsActive(true);
        }
        entity.setValue(request.getValue());
        entity.setDescription(request.getDescription());
        repository.update(entity);
    }

    @Override
    public void softDelete(String id) {
        repository.softDelete(id);
    }

    @Override
    public void restore(String id) {
        DictionaryEntity entity = repository.getById(id);
        if (entity != null && !entity.getIsActive()) {
            repository.restore(id);
        } else {
            throw new RuntimeException("Cannot restore: either not found or already active");
        }
    }

    private DictionaryResponse toResponse(DictionaryEntity entity) {
        DictionaryResponse response = new DictionaryResponse();
        response.setId(entity.getId());
        response.setCategory(entity.getCategory());
        response.setValue(entity.getValue());
        response.setDescription(entity.getDescription());
        response.setIsActive(entity.getIsActive());
        return response;
    }
}