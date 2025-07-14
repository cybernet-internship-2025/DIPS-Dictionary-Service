package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dictionary")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService service;

    @GetMapping
    public List<DictionaryResponse> getAll(@RequestParam(required = false) UUID id,
                                           @RequestParam(required = false) String value,
                                           @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        return service.getAll(id, value, isActive);
    }

    @PutMapping("/{id}")
    public void createOrUpdate(@PathVariable UUID id, @RequestBody DictionaryRequest request) {
        service.createOrUpdate(id, request);
    }

    @DeleteMapping("/{id}")
    public void softDelete(@PathVariable UUID id) {
        service.softDelete(id);
    }

    @PostMapping("/{id}/restore")
    public void restore(@PathVariable UUID id) {
        service.restore(id);
    }
}
