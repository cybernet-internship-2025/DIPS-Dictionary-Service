package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.PublicDictionaryEntry;
import az.cybernet.internship.dictionary.exception.DictionaryNotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.mapstruct.DictionaryEntryMapper;
import az.cybernet.internship.dictionary.model.DictionaryEntry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictionaryService {

    private final DictionaryMapper mapper; // MyBatis
    private final DictionaryEntryMapper dtoMapper; // MapStruct

    public DictionaryService(DictionaryMapper mapper, DictionaryEntryMapper dtoMapper) {
        this.mapper = mapper;
        this.dtoMapper = dtoMapper;
    }

    // Balash's commit
    public List<PublicDictionaryEntry> getAllActiveDictionaryWithLimit(String value, Boolean isActive, int limit) {
        List<DictionaryEntry> items = mapper.findAllActiveDictionaryWithLimit(value, isActive, limit);

        if (items.isEmpty()) throw new DictionaryNotFoundException("Dictionary not found");

        return items.stream().map(dtoMapper::toDto).collect(Collectors.toList());
    }

    public PublicDictionaryEntry restoreDictionary(String id) {
        mapper.restore(id);
        DictionaryEntry dictionary = mapper.findById(id);
        return dtoMapper.toDto(dictionary);
    }


    // Goychek's commit

    // Как-то здесь меня забыли (┬┬﹏┬┬)
    public void saveOrUpdate(PublicDictionaryEntry body) {
        if (body.getValue() == null || body.getValue().trim().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field 'value' cannot be empty");

        DictionaryEntry entry = dtoMapper.toEntity(body);

        if (body.getId() == null) mapper.insert(entry);
        else if (mapper.update(entry) == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry with id " + body.getId() + " not found");
    }
}
