package az.cybernet.internship.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dictionaries")
public class Controller {

    @Autowired
    private DictionaryService service;

    @GetMapping
    public List<DictionaryEntry> getAllActive(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String value,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false, defaultValue = "10") Integer limit
    ) {
        return service.getList(id, value, isActive, limit);
    }

    @PutMapping
    public void put(
            @RequestParam Long id,
            @RequestParam String value,
            @RequestParam(required = false) String description
    ) {
        service.saveOrUpdate(id, value, description);
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
