package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.req.DictionaryReq;
import az.cybernet.internship.dictionary.dto.resp.DictionaryResp;


import java.util.List;
import java.util.UUID;

public interface DictionaryService {

    List<DictionaryResp> findDictionaries(UUID id, String value, Boolean isActive, Integer limit);

    DictionaryResp updateDictionary(DictionaryReq dictionary);

    DictionaryResp restoreDictionary(UUID id);

    DictionaryResp delete(UUID id);
}
