package az.cybernet.internship.dictionary.controller;


import az.cybernet.internship.dictionary.model.request.CreateDictionaryRequest;
import az.cybernet.internship.dictionary.model.request.UpdateDescriptionRequest;
import az.cybernet.internship.dictionary.model.response.DictionaryResponse;
import az.cybernet.internship.dictionary.service.DictionaryServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/dictionary")
@RequiredArgsConstructor
public class DictionaryController {
    private final DictionaryServiceHandler descriptionService;

    @GetMapping("/{id}")
    public DictionaryResponse getDictionary (@PathVariable Long id) {
        return descriptionService.getDictionary(id);
    }

    @PutMapping("/{id}/description")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDescription(@PathVariable Long id, @RequestBody UpdateDescriptionRequest request) {
        descriptionService.updateDescription(id, request );
    }

    @DeleteMapping("/{id}")
    public void deleteDictionary(@PathVariable Long id) {
        descriptionService.deleteDictionary(id);

    }

    @PostMapping("/{id}/restore")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void restoreictionary(@PathVariable Long id) {
        descriptionService.restoreDictionary(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDictionary (@RequestBody CreateDictionaryRequest request) {
        descriptionService.saveDictionary(request);
    }

}
