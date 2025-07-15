package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.exception.AlreadyActiveException;
import az.cybernet.internship.dictionary.exception.AlreadyInactiveException;
import az.cybernet.internship.dictionary.exception.DictionaryNotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.mapstruct.DictionaryEntryMapper;
import az.cybernet.internship.dictionary.model.Dictionary;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DictionaryService {

    private final DictionaryMapper mapper; // MyBatis
    private final DictionaryEntryMapper dtoMapper; // MapStruct

    // Balash's commit

    public List<DictionaryResponse> getAllActiveDictionaryWithLimit(String value, Boolean isActive, int limit) {
        List<Dictionary> items = mapper.findAllActiveDictionaryWithLimit(value, isActive, limit);

        if (items.isEmpty()) throw new DictionaryNotFoundException("Dictionary not found");

        return items.stream().map(dtoMapper::toDto).collect(Collectors.toList());
    }

    // Goychek's commit

    public void deleteDictionary(UUID uuid) {
        Dictionary entity = mapper.findById(uuid);
        if (entity == null) {
            throw new EntityNotFoundException("Entity not found with id: " + uuid);
        } else if (!entity.getIsActive()) {
            throw new AlreadyInactiveException("Entity is already inactive with id: " + uuid);
        }
        mapper.softDelete(uuid, LocalDateTime.now());
    }

    public void restoreDictionary(UUID uuid) {
        Dictionary entity = mapper.findById(uuid);
        if (entity == null) {
            throw new EntityNotFoundException("Entity not found with id: " + uuid);
        } else if (entity.getIsActive()) {
            throw new AlreadyActiveException("Entity is already active with id: " + uuid);
        }
        mapper.restore(uuid);
    }

    // Huseyn's commit
    // Как-то здесь меня забыли (┬┬﹏┬┬)
    public void saveOrUpdate(DictionaryRequest body) {
        if (body.getValue() == null || body.getValue().trim().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field 'value' cannot be empty");

        Dictionary entry = dtoMapper.toEntity(body);

        if (body.getId() == null) mapper.insert(entry);
        else if (mapper.update(entry) == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry with id " + body.getId() + " not found");

    }
}


