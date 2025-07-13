package service;

import DictionaryDto.DictionaryRequest;
import DictionaryDto.DictionaryResponse;

import model.Dictionary;
import org.springframework.stereotype.Service;
import repository.DictionaryRepository;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictionaryService {

    private final DictionaryRepository repository;

    public DictionaryService(DictionaryRepository repository) {
        this.repository = repository;
    }

    public List<DictionaryResponse> findAll(String id, String value, Boolean isActive, Integer limit) {
        return repository.findAll(id, value, isActive, limit)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void saveOrUpdate(DictionaryRequest request) {
        if (request.getValue() == null || request.getValue().isBlank()) {
            throw new IllegalArgumentException("Value must not be empty");
        }

        Dictionary existing = null;
        if (request.getId() != null) {
            existing = repository.findById(request.getId());
            if (existing == null) {
                throw new IllegalArgumentException("No dictionary entry found for id: " + request.getId());
            }
        }

        Dictionary dict = new Dictionary();
        dict.setId(request.getId() != null ? request.getId() : "invoice_status_" + request.getValue().toLowerCase());
        dict.setCategory("Invoice Status");
        dict.setValue(request.getValue());
        dict.setDescription(request.getDescription());
        dict.setIsActive(true);

        if (existing != null) {
            repository.update(dict);
        } else {
            repository.insert(dict);
        }
    }

    public void softDelete(String id) {
        Dictionary entry = repository.findById(id);
        if (entry == null) {
            throw new IllegalArgumentException("Dictionary entry not found for deletion.");
        }
        repository.softDelete(id, Instant.now().toString());
    }

    public void restore(String id) {
        Dictionary entry = repository.findById(id);
        if (entry == null) {
            throw new IllegalArgumentException("Dictionary entry does not exist.");
        }
        if (Boolean.TRUE.equals(entry.getIsActive())) {
            throw new IllegalArgumentException("Dictionary entry is already active.");
        }
        repository.restore(id);
    }

    private DictionaryResponse toResponse(Dictionary dictionary) {
        DictionaryResponse response = new DictionaryResponse();
        response.setId(dictionary.getId());
        response.setCategory(dictionary.getCategory());
        response.setValue(dictionary.getValue());
        response.setDescription(dictionary.getDescription());
        response.setIsActive(dictionary.getIsActive());
        return response;
    }
}
