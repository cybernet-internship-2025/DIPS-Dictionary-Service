package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.PublicDictionaryEntry;
import az.cybernet.internship.dictionary.service.DictionaryService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dictionaries")
public class DictionaryController {

    private final DictionaryService service;

    public DictionaryController(DictionaryService service) {
        this.service = service;
    }

    // Balash commited
    @GetMapping
    public ResponseEntity<List<PublicDictionaryEntry>> getAllActiveDictionaryWithLimit(@RequestParam String value, @RequestParam Boolean isActive, @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(service.getAllActiveDictionaryWithLimit(value, isActive, limit));
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<PublicDictionaryEntry> restoreDictionary(@PathVariable String id) {
        return ResponseEntity.ok(service.restoreDictionary(id));
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
    public void put(@RequestBody PublicDictionaryEntry body) {
        service.saveOrUpdate(body);
    }
}
