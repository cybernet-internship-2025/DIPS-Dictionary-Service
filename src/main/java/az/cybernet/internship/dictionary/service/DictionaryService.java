package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.exception.DictionaryBadRequestException;
import az.cybernet.internship.dictionary.exception.DictionaryNotFoundException;
import az.cybernet.internship.dictionary.model.DictionaryEntity;
import az.cybernet.internship.dictionary.repository.DictionaryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DictionaryService {

    private final DictionaryRepository repository;

    public DictionaryService(DictionaryRepository repository) {
        this.repository = repository;
    }

    public void createOrUpdate(UUID id, DictionaryRequest request) {
        if (request.getValue() == null || request.getValue().trim().isEmpty()) {
            throw new DictionaryBadRequestException("Value field cannot be empty.");
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
            throw new DictionaryNotFoundException("Entry not found for id: " + id);
        }
        repository.softDelete(id);
    }

    public void restore(UUID id) {
        DictionaryEntity entity = repository.getById(id);
        if (entity == null) {
            throw new DictionaryNotFoundException("Entry not found for id: " + id);
        }
        if (Boolean.TRUE.equals(entity.getIsActive())) {
            throw new DictionaryBadRequestException("Entry is already active.");
        }
        repository.restore(id);
    }
}
