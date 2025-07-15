package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.dto.resp.DictionaryResp;
import az.cybernet.internship.dictionary.entity.Dictionary;
import az.cybernet.internship.dictionary.exception.DictionaryNotFoundException;
import az.cybernet.internship.dictionary.exception.InputValueMissingException;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.mapstruct.DictionaryMap;
import az.cybernet.internship.dictionary.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryMapper dictionaryMapper;

    private final DictionaryMap dictionaryMap;

    public DictionaryServiceImpl(DictionaryMapper dictionaryMapper, DictionaryMap dictionaryMap) {
        this.dictionaryMapper = dictionaryMapper;
        this.dictionaryMap = dictionaryMap;
    }

    @Override
    public List<DictionaryResp> findDictionaries(UUID id, String value, Boolean isActive, Integer limit) {
        log.info("Finding dictionaries with filters - id: {}, value: {}, isActive: {}, limit: {}"
                , id, value, isActive, limit);
        if (isActive == null) {
            isActive = true;
        }
        var result = dictionaryMapper.findByFilters(id, value, isActive, limit);
        if (id != null && result.isEmpty()) {
            log.warn("No dictionary entry found for id: {}", id);
            throw new DictionaryNotFoundException("Dictionary entry not found for id: " + id);
        }
        var resp = dictionaryMap.toDto(result);
        log.info("Found dictionary entries: {}", resp);
        return resp;
    }

    @Override
    @Transactional
    public DictionaryResp updateDictionary(Dictionary dictionary) {
        log.info("Validating input");
        if (dictionary.getId() == null || dictionary.getValue() == null) {
            throw new InputValueMissingException("Missing required params:" +
                    (dictionary.getId() == null ? " Id" : "") +
                    (dictionary.getValue() == null ? " Value" : ""));
        } log.info("Input validation successful");

        DictionaryResp dictionaryResponse = dictionaryMapper.updateDictionary(dictionary);

        log.info("Checking database response");
        if (dictionaryResponse == null) {
            log.warn("Dictionary entry not found for update with id: {}", dictionary.getId());
            throw new DictionaryNotFoundException("Dictionary entry not found for update with id: "
                    + dictionary.getId());
        }
        log.info("Successfully updated dictionary entry with id: {}", dictionary.getId());
        return dictionaryResponse;
    }

    @Override
    @Transactional
    public DictionaryResp restoreDictionary(UUID id) {
        log.info("Trying to restore dictionary with id: {}", id);
        DictionaryResp dictionaryResponse = dictionaryMapper.restoreDictionary(id);

        if(dictionaryResponse == null) {
            log.warn("Dictionary entry not found for restoration with id: {}", id);
            throw new DictionaryNotFoundException("Dictionary entry not found for restoration with id: " + id);
        }

        log.info("Successfully restored dictionary entry");
        return dictionaryResponse;
    }

    @Override
    @Transactional
    public DictionaryResp delete(UUID id) {
        log.info("Deleting dictionary with id: {}", id);
        DictionaryResp deleted = dictionaryMapper.delete(id);
        if (deleted == null) {
            log.warn("Dictionary entry not found for deletion with id: {}", id);
            throw new DictionaryNotFoundException("Dictionary entry not found for id: " + id);
        }
        log.info("Successfully deleted dictionary with id: {}", id);
        return deleted;
    }
}
