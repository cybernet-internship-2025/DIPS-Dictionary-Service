package az.cybernet.internship.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DictionaryService {

    @Autowired
    private Repository repository;

    public List<Entry> getList(Long id, String value, Boolean isActive, Integer limit) {
        return repository.list(id, value, isActive, limit != null ? limit : 100); // значение по умолчанию
    }

    public void saveOrUpdate(Entry entry) {
        if (entry.getValue() == null || entry.getValue().trim().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field 'value' cannot be empty");

        Entry existing = entry.getId() != null ? repository.findById(entry.getId()) : null;

        if (entry.getId() == null) {
            // Новый элемент
            repository.insert(entry);
        } else if (existing != null) {
            // Обновляем существующую запись
            entry.setIsActive(existing.getIsActive()); // чтобы не сбросить isActive
            entry.setDeletedAt(existing.getDeletedAt());
            repository.update(entry);
        } else {
            // Нет такой записи
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry with id " + entry.getId() + " not found");
        }
    }

    public void delete(Long id) {
        Entry entry = repository.findById(id);
        if (entry == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found");
        if (!Boolean.TRUE.equals(entry.getIsActive()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry already inactive");

        repository.softDelete(id);
    }

    public void restore(Long id) {
        Entry entry = repository.findById(id);
        if (entry == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found");
        if (Boolean.TRUE.equals(entry.getIsActive()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry is already active");

        repository.restore(id);
    }
}
