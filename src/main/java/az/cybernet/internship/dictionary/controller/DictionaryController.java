package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.DictionaryResponse;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import az.cybernet.internship.dictionary.service.DictionaryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DictionaryController {
    private final DictionaryService service;

    public DictionaryController(DictionaryService service) {
        this.service = service;
    }

    @GetMapping("/dictionaries")
    public ResponseEntity<List<DictionaryResponse>> getAll(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String value,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) Integer limit) {
        return ResponseEntity.ok(service.getDictionaries(id, value, isActive, limit));
    }

//    @PutMapping
//    public ResponseEntity<Void> saveOrUpdate(@RequestBody DictionaryRequest request) {
//        az.cybernet.internship.dictionary.service.saveOrUpdate(request);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> softDelete(@PathVariable String id) {
//        az.cybernet.internship.dictionary.service.softDelete(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/{id}/restore")
//    public ResponseEntity<Void> restore(@PathVariable String id) {
//        az.cybernet.internship.dictionary.service.restore(id);
//        return ResponseEntity.ok().build();
//    }
}
