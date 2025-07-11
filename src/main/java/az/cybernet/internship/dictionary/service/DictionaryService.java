package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.entity.DictionaryEntity;
import az.cybernet.internship.dictionary.mapstruct.DictionaryDtoMapper;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.service.dto.DictionaryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryService {
    private final DictionaryMapper mapper;
    private final DictionaryDtoMapper dtoMapper;

    public DictionaryEntity getDictionaryById(Integer id) {
        return mapper.findById(id);
    }

    public List<DictionaryEntity> getAllDictionary(Integer id, String value,
                                                   Boolean isActive, Integer limit) {
        return mapper.findAll(id, value, isActive, limit);
    }

    public Integer addDictionary(DictionaryRequest request) {
        DictionaryEntity entity = dtoMapper.toEntity(request);
        return mapper.insert(entity);
    }

    public Integer updateDictionary(Integer id, DictionaryRequest request) {
        return mapper.update(id, request);
    }

    public Integer deleteDictionary(Integer id) {
        return mapper.delete(id);
    }
}
