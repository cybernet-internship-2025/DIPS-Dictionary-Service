package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.entity.Dictionary;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.service.DictionaryService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryMapper dictionaryMapper;

    public DictionaryServiceImpl(DictionaryMapper dictionaryMapper) {
        this.dictionaryMapper = dictionaryMapper;
    }

    @Override
    public List<Dictionary> findDictionaries(UUID id, String value, Boolean isActive, Integer limit) {
        if (isActive == null) {
            isActive = true;
        }
        return dictionaryMapper.findByFilters(id, value, isActive, limit);
    }
}
