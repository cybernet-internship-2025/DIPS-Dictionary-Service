package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.exception.AlreadyInactiveException;
import az.cybernet.internship.dictionary.exception.DictionaryNotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.model.Dictionary;
import az.cybernet.internship.dictionary.service.DictionaryService;
import jakarta.persistence.EntityNotFoundException;
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

    // Balash's commit
    @Override
    public List<DictionaryResponse> getAllActiveDictionaryWithLimit(String value, Boolean isActive, int limit) {
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

    // Goychek's commit
    @Override
    public void deleteDictionary(UUID uuid) {
        Dictionary entity = mapper.findById(uuid);
        if (entity == null) {
            throw new EntityNotFoundException("Entity not found with id: " + uuid);
        } else if (!entity.getIsActive()) {
            throw new AlreadyInactiveException("Entity is already inactive with id: " + uuid);
        }
        mapper.softDelete(uuid, LocalDateTime.now());
    }
}

// Huseyn's commit
