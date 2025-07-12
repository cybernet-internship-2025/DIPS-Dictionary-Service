package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.resp.DictionaryResp;
import az.cybernet.internship.dictionary.entity.Dictionary;


import java.util.List;
import java.util.UUID;

public interface DictionaryService {

    List<DictionaryResp> findDictionaries(UUID id, String value, Boolean isActive, Integer limit);

    DictionaryResp updateDictionary(Dictionary dictionary);

    DictionaryResp delete(UUID id);
}
