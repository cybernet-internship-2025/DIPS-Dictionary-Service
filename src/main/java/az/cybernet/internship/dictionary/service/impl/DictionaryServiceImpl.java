package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.exception.NotFoundException;

import az.cybernet.internship.dictionary.model.Dictionary;
import az.cybernet.internship.dictionary.repository.DictionaryRepository;
import az.cybernet.internship.dictionary.service.DictionaryService;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryRepository repository;

    public DictionaryServiceImpl(DictionaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Dictionary> findAll(UUID id, String value, Boolean isActive, Integer limit) {
        if (limit == null || limit <= 0) limit = 100;
        return repository.findAll(id, value, isActive, limit);
    }

    @Override
    public Dictionary save(Dictionary dictionary) {
        if (dictionary.getValue() == null || dictionary.getValue().isBlank()) {
            throw new ValidationException("Value must not be empty");
        }

        if (dictionary.getId() == null) {
            dictionary.setId(UUID.randomUUID());
            dictionary.setIsActive(true);
            dictionary.setDeletedAt(null);
            repository.insert(dictionary);
        } else {
            Dictionary existing = repository.findById(dictionary.getId());
            if (existing == null) {
                throw new ValidationException("Cannot update. Dictionary entry not found.");
            }
            repository.update(dictionary);
        }
        return dictionary;
    }

    @Override
    public void delete(UUID id) {
        Dictionary dict = repository.findById(id);
        if (dict == null) throw new NotFoundException("Dictionary not found");
        if (!Boolean.TRUE.equals(dict.getIsActive())) return;
        dict.setIsActive(false);
        dict.setDeletedAt(LocalDateTime.now());
        repository.update(dict);
    }

    @Override
    public void restore(UUID id) {
        Dictionary dict = repository.findById(id);
        if (dict == null) throw new NotFoundException("Dictionary not found");
        if (Boolean.TRUE.equals(dict.getIsActive())) {
            throw new ValidationException("Entry is already active");
        }
        dict.setIsActive(true);
        dict.setDeletedAt(null);
        repository.update(dict);
    }

    @Override
    public Optional<Dictionary> findById(UUID id) {
        return Optional.ofNullable(repository.findById(id));
    }
}