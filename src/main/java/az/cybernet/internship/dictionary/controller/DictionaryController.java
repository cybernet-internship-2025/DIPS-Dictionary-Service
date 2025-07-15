package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.service.DictionaryService;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/dictionaries")
public class DictionaryController {

    private final DictionaryService service;

    public DictionaryController(DictionaryService service) {
        this.service = service;
    }

    // Balash commited
    @GetMapping
    public ResponseEntity<List<DictionaryResponse>> getAllActiveDictionaryWithLimit(@RequestParam String value, @RequestParam Boolean isActive, @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(service.getAllActiveDictionaryWithLimit(value, isActive, limit));
    }

    //Goychek commited
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/restore")
    public void restoreDictionary(@PathVariable UUID id) {
        service.restoreDictionary(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteDictionary(@PathVariable UUID id) {
        service.deleteDictionary(id);
    }

    //Huseyn commited
    // Может когда-нибудь и здесь это понабиться =￣ω￣=

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void put(@RequestBody DictionaryRequest body) {
        service.saveOrUpdate(body);
    }
}
