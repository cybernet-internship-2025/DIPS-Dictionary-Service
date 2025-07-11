package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.exception.DictionaryNotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.model.Dictionary;
import az.cybernet.internship.dictionary.repository.DictionaryRepository;
import az.cybernet.internship.dictionary.service.DictionaryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
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

    @Override
    public DictionaryResponse restoreDictionary(UUID uuid) {
        mapper.restore(uuid);

//        if (updated.equals(0)) {
//            throw new DictionaryNotFoundException("Dictionary not found");
//        }

        Dictionary dictionary = mapper.findById(uuid);

        DictionaryResponse response = new DictionaryResponse();
        response.setId(dictionary.getId());
        response.setCategory(dictionary.getCategory());
        response.setValue(dictionary.getValue());
        response.setDescription(dictionary.getDescription());

        return response;
    }
}
