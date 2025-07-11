package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.request.DictionaryEntryRequestDTO;
import az.cybernet.internship.dictionary.model.DictionaryEntry;
import az.cybernet.internship.dictionary.service.DictionaryEntryService;
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
    public ResponseEntity<List<DictionaryEntry>> getAll() {
        List<DictionaryEntry> all = dictionaryEntryService.selectAll()
                .stream()
                .filter(DictionaryEntry::isActive)
                .toList();
        return ResponseEntity.ok(all);
    }

    @PutMapping
    public ResponseEntity<?> createOrUpdate(@RequestBody DictionaryEntryRequestDTO dto) {
        if (dto.getValue() == null || dto.getValue().isBlank()) {
            return ResponseEntity.badRequest().body("Value must not be blank");
        }

        DictionaryEntry entry = DictionaryEntry.builder()
                .id(dto.getId() != null ? dto.getId() : UUID.randomUUID())
                .value(dto.getValue())
                .description(dto.getDescription())
                .categoryName(dto.getCategoryName())
                .isActive(true)
                .deletedAt(null)
                .build();

        if (dto.getId() != null) {
            if (dictionaryEntryService.selectById(dto.getId()) == null) {
                return ResponseEntity.badRequest().body("No entry with given ID exists");
            }
            dictionaryEntryService.update(entry);
            return ResponseEntity.ok("Updated successfully");
        }

        dictionaryEntryService.insert(entry);
        return ResponseEntity.ok("Created successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> softDelete(@PathVariable UUID id) {
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
