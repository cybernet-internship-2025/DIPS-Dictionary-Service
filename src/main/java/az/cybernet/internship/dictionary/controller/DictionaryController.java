package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.service.impl.DictionaryServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dictionaries")
public class DictionaryController {

    private final DictionaryServiceImpl service;

    public DictionaryController(DictionaryServiceImpl service) {
        this.service = service;
    }

    // Balash commited
    @GetMapping
    public ResponseEntity<List<DictionaryResponse>> getAllActiveDictionaryWithLimit(@RequestParam String value, @RequestParam Boolean isActive, @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(service.getAllActiveDictionaryWithLimit(value, isActive, limit));
    }

    @PostMapping("/{id}/restore")
    public void restoreDictionary(@PathVariable UUID id) {
        service.restoreDictionary(id);
    }

    //Goychek commited


    // Может когда-нибудь и здесь это понабиться =￣ω￣=
    @Getter
    @Setter
    public static class DictionaryFilter {
        private Long id;
        private String value;
        private Boolean isActive;
        private Integer limit;
    }

    @PutMapping
    public void put(@RequestBody DictionaryRequest body) {
        service.saveOrUpdate(body);
    }
}
