package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.*;
import az.cybernet.internship.dictionary.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dictionary")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;
    @GetMapping
    public ResponseEntity<List<DictionaryResponse>> findAll() {
        return ResponseEntity.ok(dictionaryService.findAll());
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<DictionaryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(dictionaryService.findById(id));
    }
    @PutMapping
    public ResponseEntity<DictionaryResponse> saveOrUpdate( @Valid @RequestBody DictionaryRequest dictionaryRequest) {
        return ResponseEntity.ok(dictionaryService.saveOrUpdate(dictionaryRequest));

    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<DictionaryResponse> delete(@PathVariable  Long id) {
        dictionaryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping(path = "{id}")
    public ResponseEntity<DictionaryResponse> restore(@PathVariable Long id) {
       dictionaryService.restore(id);
       return ResponseEntity.ok().build();

    }

}
