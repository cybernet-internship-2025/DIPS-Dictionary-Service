package az.cybernet.internship.dictionary.controller;


import az.cybernet.internship.dictionary.dto.dictionary.createDictionary.CreateDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.createDictionary.CreateDictionaryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.filterDictionary.FilterDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.filterDictionary.FilterDictionaryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.getByCategoryId.GetByCategoryIdResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.getById.GetDictionaryByIdResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.restore.RestoreDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.restore.RestoreDictionaryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.softDelete.SoftDeleteDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.softDelete.SoftDeleteDictionaryResponseBean;
import az.cybernet.internship.dictionary.dto.dictionary.updateDictionary.UpdateDictionaryRequestBean;
import az.cybernet.internship.dictionary.dto.dictionary.updateDictionary.UpdateDictionaryResponseBean;
import az.cybernet.internship.dictionary.service.DictionaryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dictionaries")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryServiceImpl dictionaryService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetDictionaryByIdResponseBean getById(@PathVariable("id")UUID id) {
        return dictionaryService.getById(id);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<FilterDictionaryResponseBean> filterDictionary(@RequestBody @Valid FilterDictionaryRequestBean request) {
        return dictionaryService.filterDictionary(request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateDictionaryResponseBean put(@RequestBody @Valid CreateDictionaryRequestBean request) {
        return dictionaryService.createDictionary(request);
    }

    @GetMapping("/category-id/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetByCategoryIdResponseBean> getByCategoryId(@PathVariable("categoryId") UUID categoryId) {
        return dictionaryService.getByCategoryId(categoryId);
    }

    @PutMapping("/restore")
    @ResponseStatus(HttpStatus.OK)
    public RestoreDictionaryResponseBean restoreDictionary(@RequestBody @Valid RestoreDictionaryRequestBean request) {
        return dictionaryService.restoreDictionary(request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public SoftDeleteDictionaryResponseBean softDelete(@RequestBody @Valid SoftDeleteDictionaryRequestBean request){
        return dictionaryService.softDelete(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdateDictionaryResponseBean updateDictionary(@RequestBody @Valid UpdateDictionaryRequestBean request){
        return dictionaryService.updateDictionary(request);
    }
}