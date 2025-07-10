package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.entity.Dictionary;
import az.cybernet.internship.dictionary.service.DictionaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dictionaries")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping
    public ResponseEntity<List<Dictionary>> getDictionaries(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String value,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false, defaultValue = "100") Integer limit
    ) {
        List<Dictionary> dictionaries = dictionaryService.findDictionaries(id, value, isActive, limit);
        return ResponseEntity.ok(dictionaries);
    }
}
