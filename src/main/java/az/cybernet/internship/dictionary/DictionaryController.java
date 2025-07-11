package az.cybernet.internship.dictionary;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dictionaries")
public class DictionaryController {

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
