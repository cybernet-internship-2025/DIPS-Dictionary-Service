package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.request.DictionaryEntryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryEntryResponseDTO;
import az.cybernet.internship.dictionary.service.DictionaryEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> createOrUpdate(@Valid @RequestBody DictionaryEntryRequestDTO entryRequestDTO) {
        if (entryRequestDTO.getId() != null) {
            dictionaryEntryService.update(entryRequestDTO);
            return ResponseEntity.ok("Updated successfully");
        }

        dictionaryEntryService.insert(entryRequestDTO);
        return ResponseEntity.ok("Created successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        dictionaryEntryService.delete(id);
        return ResponseEntity.ok("Deleted (soft) successfully");
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<?> restore(@PathVariable UUID id) {
        try {
            dictionaryEntryService.restore(id);
            return ResponseEntity.ok("Restored successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Entry is already active");
        }
    }
}
