package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.model.Dictionary;
import az.cybernet.internship.dictionary.service.DictionaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dictionaries")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping
    public ResponseEntity<List<Dictionary>> findAll(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String value,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) Integer limit
    ) {
        return ResponseEntity.ok(dictionaryService.findAll(id, value, isActive, limit));
    }

    @PutMapping
    public ResponseEntity<Dictionary> save(@RequestBody Dictionary dictionary) {
        Dictionary saved = dictionaryService.save(dictionary);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        dictionaryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable UUID id) {
        dictionaryService.restore(id);
        return ResponseEntity.ok().build();
    }
}