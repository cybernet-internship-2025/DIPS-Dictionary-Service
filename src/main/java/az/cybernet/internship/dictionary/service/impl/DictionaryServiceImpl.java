package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.exception.DictionaryNotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.model.Dictionary;
import az.cybernet.internship.dictionary.repository.DictionaryRepository;
import az.cybernet.internship.dictionary.service.DictionaryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryRepository repository;
    private final DictionaryMapper mapper;

    public DictionaryServiceImpl(DictionaryRepository repository, DictionaryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DictionaryResponse> getAllActiveDictionaryWithLimit(String value, Boolean isActive,int limit) {
        List<Dictionary> items = mapper.findAllActiveDictionaryWithLimit(value, isActive, limit);

        if (items.isEmpty()) throw new DictionaryNotFoundException("Dictionay not found");

        return items.stream()
                .map(item -> new DictionaryResponse(
                        item.getId(),
                        item.getCategory(),
                        item.getValue(),
                        item.getDescription()
                ))
                .collect(Collectors.toList());
    }
}
