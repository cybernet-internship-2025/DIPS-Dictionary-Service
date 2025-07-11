package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dictionaries")
public class DictionaryController {

    private final DictionaryService service;

    @Autowired
    public DictionaryController(DictionaryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DictionaryResponse>> getAllActiveDictionaryWithLimit(
            @RequestParam String value,
            @RequestParam Boolean isActive,
            @RequestParam(defaultValue = "10") int limit) {

        return ResponseEntity.ok(service.getAllActiveDictionaryWithLimit(value, isActive, limit));
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<DictionaryResponse> restoreDictionary(@PathVariable UUID id) {
        return ResponseEntity.ok(service.restoreDictionary(id));
    }
}
