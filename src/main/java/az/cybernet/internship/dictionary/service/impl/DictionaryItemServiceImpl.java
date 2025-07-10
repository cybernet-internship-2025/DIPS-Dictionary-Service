package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.dto.DictionaryItemRequest;
import az.cybernet.internship.dictionary.dto.DictionaryItemResponse;
import az.cybernet.internship.dictionary.mapper.DictionaryItemMapper;
import az.cybernet.internship.dictionary.repository.DictionaryItemRepository;
import az.cybernet.internship.dictionary.service.DictionaryItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryItemServiceImpl implements DictionaryItemService {

    private final DictionaryItemRepository repository;
    private final DictionaryItemMapper mapper;

    public DictionaryItemServiceImpl(DictionaryItemRepository repository, DictionaryItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DictionaryItemResponse> getAllActiveDictionary() {

        return List.of();
    }

    @Override
    public DictionaryItemResponse createOrUpdate(DictionaryItemRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DictionaryItemResponse restore(Long id) {
        return null;
    }
}
