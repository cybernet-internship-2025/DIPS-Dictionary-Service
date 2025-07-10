package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.dto.resp.DictionaryResp;
import az.cybernet.internship.dictionary.exception.DictionaryNotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.mappers.DictionaryMap;
import az.cybernet.internship.dictionary.service.DictionaryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryMapper dictionaryMapper;

    private final DictionaryMap dictionaryMap;

    public DictionaryServiceImpl(DictionaryMapper dictionaryMapper, DictionaryMap dictionaryMap) {
        this.dictionaryMapper = dictionaryMapper;
        this.dictionaryMap = dictionaryMap;
    }

    @Override
    public List<DictionaryResp> findDictionaries(UUID id, String value, Boolean isActive, Integer limit) {
        if (isActive == null) {
            isActive = true;
        }
        var result = dictionaryMapper.findByFilters(id, value, isActive, limit);
        if (id != null && result.isEmpty()) {
            throw new DictionaryNotFoundException("Dictionary entry not found for id: " + id);
        }
        return dictionaryMap.toDto(result);
    }
}
