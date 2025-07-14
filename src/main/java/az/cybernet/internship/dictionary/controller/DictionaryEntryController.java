package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.request.DictionaryEntryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryEntryResponseDTO;
import az.cybernet.internship.dictionary.service.DictionaryEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dictionaries")
@RequiredArgsConstructor
public class DictionaryEntryController {

    private final DictionaryEntryService dictionaryEntryService;

    @GetMapping
    public ResponseEntity<List<DictionaryEntryResponseDTO>> selectAll() {
        return ResponseEntity.ok(dictionaryEntryService.selectAll());
    }

    @PutMapping
    public ResponseEntity<DictionaryEntryResponseDTO> createOrUpdate(
            @Valid @RequestBody DictionaryEntryRequestDTO entryRequestDTO) {
        DictionaryEntryResponseDTO result;

        if (entryRequestDTO.getId() != null) {
            result = dictionaryEntryService.update(entryRequestDTO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        result = dictionaryEntryService.insert(entryRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        dictionaryEntryService.delete(id);
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<DictionaryEntryResponseDTO> restore(@PathVariable UUID id) {
        DictionaryEntryResponseDTO restored = dictionaryEntryService.restore(id);
        return new ResponseEntity<>(restored, HttpStatus.OK);
    }
}
