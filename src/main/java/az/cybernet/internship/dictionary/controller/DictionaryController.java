package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class DictionaryController {

    private final DictionaryService service;
    public DictionaryController(DictionaryService service) {
        this.service = service;
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
