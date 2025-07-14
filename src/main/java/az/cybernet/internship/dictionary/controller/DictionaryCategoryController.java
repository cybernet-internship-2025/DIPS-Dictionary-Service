package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.request.DictionaryCategoryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryCategoryResponseDTO;
import az.cybernet.internship.dictionary.service.DictionaryCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class DictionaryCategoryController {
    private final DictionaryCategoryService dictionaryCategoryService;

    @GetMapping
    public ResponseEntity<List<DictionaryCategoryResponseDTO>> selectAll() {
        return ResponseEntity.ok(dictionaryCategoryService.selectAll());
    }

    @PutMapping
    public ResponseEntity<DictionaryCategoryResponseDTO> createOrUpdate(
            @Valid @RequestBody DictionaryCategoryRequestDTO categoryRequestDTO) {
        DictionaryCategoryResponseDTO result;

        if (categoryRequestDTO.getId() != null) {
            result = dictionaryCategoryService.update(categoryRequestDTO);
            return ResponseEntity.ok(result);
        }

        result = dictionaryCategoryService.insert(categoryRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        dictionaryCategoryService.delete(id);
    }
}
