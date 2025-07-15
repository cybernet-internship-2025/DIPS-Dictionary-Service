package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.model.dto.DictionaryResponseDto;
import az.cybernet.internship.dictionary.service.DictionaryService;
import az.cybernet.internship.dictionary.model.dto.DictionaryCreateUpdateDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dictionaries")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping
    public ResponseEntity<List<DictionaryResponseDto>> getDictionaries(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String value,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) Integer limit) {

        List<DictionaryResponseDto> entries = dictionaryService.retrieveDictionaries(id, value, isActive, limit);

        return ResponseEntity.ok(entries);
    }

    //hello, comment test.

    @PutMapping
    public ResponseEntity<DictionaryResponseDto> createOrUpdateDictionary(@RequestBody @Valid DictionaryCreateUpdateDto dto) {
        DictionaryResponseDto resultDto = dictionaryService.createOrUpdateDictionary(dto);
        // If an ID was not present in the request, it's a creation (201)
        // Otherwise it's an update (200)
        if (dto.getId() == null) {
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(resultDto, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDictionary(@PathVariable UUID id) {
        dictionaryService.softDeleteDictionary(id);
        return ResponseEntity.noContent().build(); // Standard HTTP 204 No Content response
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<DictionaryResponseDto> restoreDictionary(@PathVariable UUID id) {
        DictionaryResponseDto restoredDto = dictionaryService.restoreDictionary(id);
        return ResponseEntity.ok(restoredDto);
    }
}
