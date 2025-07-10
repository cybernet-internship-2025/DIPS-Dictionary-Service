package az.cybernet.internship.dictionary.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dictionaries")
public class DictionaryController {
    @GetMapping
    public ResponseEntity<?> findAllDictionaries(){
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> saveDictionary(@PathVariable UUID id){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDictionary(@PathVariable UUID id){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<?> restoreDictionary(@PathVariable UUID id){
        return ResponseEntity.ok().build();
    }
}
