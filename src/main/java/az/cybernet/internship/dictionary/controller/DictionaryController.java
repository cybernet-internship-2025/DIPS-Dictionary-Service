package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.entity.DictionaryEntity;
import az.cybernet.internship.dictionary.service.DictionaryService;
import az.cybernet.internship.dictionary.service.dto.DictionaryRequest;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/dictionaries")
public class DictionaryController {
    private final DictionaryService service;

    @GetMapping("/{id}")
    public DictionaryEntity getDictionary(@PathVariable Integer id) {
        return service.getDictionaryById(id);
    }

    @GetMapping
    public List<DictionaryEntity> getAllDictionary(@RequestParam Integer id, @RequestParam String value,
                                                   @RequestParam Boolean isActive, @RequestParam Integer limit) {
        return service.getAllDictionary(id, value, isActive, limit);
    }

    @PostMapping
    public Integer insertDictionary(@RequestBody DictionaryRequest request) {
        return service.addDictionary(request);
    }

    @PutMapping("/{id}")
    public Integer updateDictionary(@PathVariable Integer id, @RequestBody DictionaryRequest request) {
        return service.updateDictionary(id, request);
    }

    @DeleteMapping("/{id}")
    public Integer deleteDictionary(@PathVariable Integer id) {
        return service.deleteDictionary(id);
    }

}
