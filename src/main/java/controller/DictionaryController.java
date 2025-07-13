package controller;

import DictionaryDto.DictionaryRequest;
import DictionaryDto.DictionaryResponse;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.DictionaryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dictionaries")
public class DictionaryController {

    private final DictionaryService service;

    public DictionaryController(DictionaryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DictionaryResponse>> getAll(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String value,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) Integer limit) {
        return ResponseEntity.ok(service.findAll(id, value, isActive, limit));
    }

    @PutMapping
    public ResponseEntity<Void> saveOrUpdate(@RequestBody DictionaryRequest request) {
        service.saveOrUpdate(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable String id) {
        service.softDelete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable String id) {
        service.restore(id);
        return ResponseEntity.ok().build();
    }
}
