package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.resp.DictionaryResp;
import az.cybernet.internship.dictionary.service.DictionaryService;
import az.cybernet.internship.dictionary.entity.Dictionary;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/dictionaries")
@Validated
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping
    public ResponseEntity<List<DictionaryResp>> getDictionaries(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String value,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false, defaultValue = "100") Integer limit
    ) {
        List<DictionaryResp> dictionaries = dictionaryService.findDictionaries(id, value, isActive, limit);
        return ok(dictionaries);
    }

    @PutMapping
    public ResponseEntity<DictionaryResp> updateDictionary(
            @RequestBody Dictionary dictionary
    ) {
        //shouldn't return this but foe testing purposes let it stay
        DictionaryResp response = dictionaryService.updateDictionary(dictionary);
        return ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DictionaryResp> delete(@PathVariable @NotBlank String id) {
        return ok(dictionaryService.delete(id));
    }
}
