package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.*;
import az.cybernet.internship.dictionary.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dictionary")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService service;

    @GetMapping
    public List<DictionaryResponse> getAll(@RequestParam(required = false) String id,
                                           @RequestParam(required = false) String value,
                                           @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        return service.getAll(id, value, isActive);
    }

    @PutMapping("/{id}")
    public void createOrUpdate(@PathVariable String id, @RequestBody DictionaryRequest request) {
        service.createOrUpdate(id, request);
    }

    @DeleteMapping("/{id}")
    public void softDelete(@PathVariable String id) {
        service.softDelete(id);
    }

    @PostMapping("/{id}/restore")
    public void restore(@PathVariable String id) {
        service.restore(id);
    }
}
