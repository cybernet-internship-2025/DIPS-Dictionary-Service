package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.exception.DictionaryNotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.mapstruct.DictionaryEntryMapper;
import az.cybernet.internship.dictionary.model.Dictionary;
import az.cybernet.internship.dictionary.service.DictionaryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryMapper mapper; // MyBatis
    private final DictionaryEntryMapper dtoMapper; // MapStruct

    public DictionaryServiceImpl(DictionaryMapper mapper, DictionaryEntryMapper dtoMapper) {
        this.mapper = mapper;
        this.dtoMapper = dtoMapper;
    }

    // Balash's commit
    public List<DictionaryResponse> getAllActiveDictionaryWithLimit(String value, Boolean isActive, int limit) {
        List<Dictionary> items = mapper.findAllActiveDictionaryWithLimit(value, isActive, limit);

        if (items.isEmpty()) throw new DictionaryNotFoundException("Dictionary not found");

        return items.stream().map(dtoMapper::toDto).collect(Collectors.toList());
    }

    // Goychek's commit

    @Override
    public void softDelete(UUID id) {

    }

    @Override
    public void restoreDictionary(UUID id) {

    }

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
