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
    public List<DictionaryEntity> get(DictionaryFilter filter) {
        return service.getList(filter.id, filter.value, filter.isActive, filter.limit);
    }

    @PutMapping
    public void put(@RequestBody DictionaryEntity entity) {
        service.saveOrUpdate(entity.getId(), entity.getValue(), entity.getDescription());
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
