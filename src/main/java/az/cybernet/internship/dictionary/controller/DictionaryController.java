package az.cybernet.internship.dictionary.controller;


import az.cybernet.internship.dictionary.DictionaryServiceApplication;
import az.cybernet.internship.dictionary.model.request.CreateDictionaryRequest;
import az.cybernet.internship.dictionary.model.request.UpdateDescriptionRequest;
import az.cybernet.internship.dictionary.model.response.DictionaryResponse;
import az.cybernet.internship.dictionary.service.DictionaryServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/dictionaries")
@RequiredArgsConstructor
public class DictionaryController {

        private final DictionaryServiceHandler dictionaryService;

        @GetMapping("/{id}")
        public DictionaryResponse getDictionary(@PathVariable Long id) {
            return dictionaryService.getDictionary(id);
        }

        @PostMapping
        public void createDictionary(@RequestBody CreateDictionaryRequest request) {
            dictionaryService.saveDictionary(request);
        }

        @PutMapping("/{id}")
        public void updateDescription(@PathVariable Long id, @RequestBody UpdateDescriptionRequest request) {
            dictionaryService.updateDescription(id, request);
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            dictionaryService.deleteDictionary(id);
        }

        @PutMapping("/{id}/restore")
        public void restore(@PathVariable Long id) {
            dictionaryService.restoreDictionary(id);
        }
    }
