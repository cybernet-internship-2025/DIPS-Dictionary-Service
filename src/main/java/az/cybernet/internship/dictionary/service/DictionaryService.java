package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.model.DictionaryEntity;
import az.cybernet.internship.dictionary.repository.DictionaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final DictionaryRepository repository;

    public List<DictionaryResponse> getAll(UUID id, String value, Boolean isActive) {
        List<DictionaryEntity> entities = repository.getAll(id, value, isActive);
        List<DictionaryResponse> responses = new ArrayList<>();

        for (DictionaryEntity entity : entities) {
            DictionaryResponse response = new DictionaryResponse();
            response.setId(entity.getId());
            response.setValue(entity.getValue());
            response.setDescription(entity.getDescription());
            response.setIsActive(entity.getIsActive());
            responses.add(response);
        }

        return responses;
    }

    public void createOrUpdate(UUID id, DictionaryRequest request) {
        if (request.getValue() == null || request.getValue().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Value field cannot be empty.");
        }

        DictionaryEntity existing = repository.getById(id);

        if (existing == null) {
            DictionaryEntity entity = new DictionaryEntity();
            entity.setId(id);
            entity.setValue(request.getValue());
            entity.setDescription(request.getDescription());
            entity.setIsActive(true);
            repository.insert(entity);
        } else {
            existing.setValue(request.getValue());
            existing.setDescription(request.getDescription());
            existing.setIsActive(true);
            repository.update(existing);
        }
    }

    public void softDelete(UUID id) {
        DictionaryEntity entity = repository.getById(id);
        if (entity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found for id: " + id);
        }
        repository.softDelete(id);
    }

    public void restore(UUID id) {
        DictionaryEntity entity = repository.getById(id);
        if (entity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found for id: " + id);
        }
        if (Boolean.TRUE.equals(entity.getIsActive())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry is already active.");
        }
        repository.restore(id);
    }
}
