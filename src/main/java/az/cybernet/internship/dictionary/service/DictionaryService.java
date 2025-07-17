package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;

import java.util.List;


public interface DictionaryService {

    List<DictionaryResponse> findAll();

    DictionaryResponse findById(Long id);

    DictionaryResponse saveOrUpdate(DictionaryRequest dictionaryRequest);

    void deleteById(Long id) throws Exception;

    void restore(Long id);

}
