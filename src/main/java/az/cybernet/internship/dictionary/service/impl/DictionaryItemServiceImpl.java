package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.dto.DictionaryItemRequest;
import az.cybernet.internship.dictionary.dto.DictionaryItemResponse;
import az.cybernet.internship.dictionary.mapper.DictionaryItemMapper;
import az.cybernet.internship.dictionary.model.DictionaryItem;
import az.cybernet.internship.dictionary.repository.DictionaryItemRepository;
import az.cybernet.internship.dictionary.service.DictionaryItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictionaryItemServiceImpl implements DictionaryItemService {

    private final DictionaryItemRepository repository;
    private final DictionaryItemMapper mapper;

    public DictionaryItemServiceImpl(DictionaryItemRepository repository, DictionaryItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DictionaryItemResponse> getAllActiveDictionaryWithLimit(int limit) {
        List<DictionaryItem> items = mapper.findAllActiveWithLimit(limit);
        return items.stream()
                .map(item -> new DictionaryItemResponse(
                        item.getId(),
                        item.getCategory(),
                        item.getValue(),
                        item.getDescription()
                ))
                .collect(Collectors.toList());
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
