package az.cybernet.internship.dictionary;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dictionaries")
public class DictionaryController {

    @Autowired
    private DictionaryService service;

    @Getter
    @Setter
    public static class DictionaryFilter {
        private Long id;
        private String value;
        private Boolean isActive;
        private Integer limit;
    }

    @GetMapping
    public List<DictionaryEntry> get(DictionaryFilter filter) {
        return service.getList(filter.id, filter.value, filter.isActive, filter.limit);
    }

    @PutMapping
    public void put(@RequestBody DictionaryEntry entry) {
        service.saveOrUpdate(entry.getId(), entry.getValue(), entry.getDescription());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/{id}/restore")
    public void restore(@PathVariable Long id) {
        service.restore(id);
    }
}
