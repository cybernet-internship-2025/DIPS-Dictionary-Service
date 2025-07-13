package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
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

    @PutMapping
    private ResponseEntity<DictionaryResponse> updateOrInsertDictionary(@RequestBody DictionaryRequest request) {
        return ResponseEntity.ok(service.updateOrInsert(request));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<DictionaryResponse> softDelete(@PathVariable UUID id) {
        return ResponseEntity.ok(service.softDelete(id));
    }
}
