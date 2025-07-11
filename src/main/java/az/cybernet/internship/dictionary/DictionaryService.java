package az.cybernet.internship.dictionary;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DictionaryService {

    private final DictionaryMapper mapper;

    public DictionaryService(DictionaryMapper mapper) {
        this.mapper = mapper;
    }

    public List<DictionaryEntry> getList(Long id, String value, Boolean isActive, Integer limit) {
        return mapper.list(id, value, isActive, limit == null || limit <= 0 ? 100 : limit);
    }

    public void saveOrUpdate(Long id, String value, String description) {
        if (value == null || value.trim().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field 'value' cannot be empty");
        if (id == null || id <= 0)
            mapper.insert(new DictionaryEntry(id, value, description));
        else if (mapper.update(new DictionaryEntry(id, value, description)) == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry with id " + id + " not found");
    }

    public void delete(Long id) {
        Boolean isActive = mapper.isActive(id);
        if (isActive == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found");
        if (!isActive) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry already inactive");

        mapper.softDelete(id);
    }

    public void restore(Long id) {
        Boolean isActive = mapper.isActive(id);
        if (isActive == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found");
        if (isActive) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry is already active");

        mapper.restore(id);
    }
}
