package az.cybernet.internship.dictionary.controller;


import az.cybernet.internship.dictionary.model.dictionary.DictionaryRequest;
import az.cybernet.internship.dictionary.model.dictionary.DictionaryResponse;
import az.cybernet.internship.dictionary.service.DictionaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dictionaries")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping("/{id}")
    public ResponseEntity<DictionaryResponse> getById(@PathVariable UUID id) {
        DictionaryResponse response = dictionaryService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DictionaryResponse>> filter(@Valid DictionaryRequest dictionaryRequest) {
        List<DictionaryResponse> result = dictionaryService.filter(dictionaryRequest);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable UUID id, @RequestBody @Valid DictionaryRequest request) {
        dictionaryService.put(id, request);
        return ResponseEntity.created(URI.create("/api/v1/dictionaries/" + id)).build();
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<DictionaryResponse>> getByCategoryId(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(dictionaryService.getByCategoryId(categoryId));
    }
}
