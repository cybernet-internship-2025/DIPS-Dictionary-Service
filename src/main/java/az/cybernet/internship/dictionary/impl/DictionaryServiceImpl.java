package az.cybernet.internship.dictionary.impl;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.repository.DictionaryRepository;
import az.cybernet.internship.dictionary.service.DictionaryService;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryRepository repository;
    private final DictionaryMapper mapper;


    @Override
    public List<DictionaryResponse> getDictionaries(String id, String value, Boolean isActive, Integer limit) {
        return mapper.toResponseMapper(repository.getDictionaries(id, value, isActive, limit));

    }

    @Override
    public void saveOrUpdate(DictionaryRequest request) {

    }
}
