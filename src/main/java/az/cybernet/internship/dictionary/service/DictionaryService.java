package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.error.AlreadyActivesException;
import az.cybernet.internship.dictionary.error.NotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.mapstruct.DictionaryMapstruct;
import az.cybernet.internship.dictionary.model.dictionary.Dictionary;
import az.cybernet.internship.dictionary.model.dictionary.DictionaryRequest;
import az.cybernet.internship.dictionary.model.dictionary.DictionaryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DictionaryService {
    private final DictionaryMapper dictionaryItemMapper;
    private final DictionaryMapstruct dictionaryMapstruct;

    public List<DictionaryResponse> getByCategoryId(UUID categoryId) {
        return dictionaryItemMapper
                .findByCategoryId(categoryId)
                .stream()
                .map(dictionaryMapstruct::toResponse)
                .collect(Collectors.toList());
    }

    public void put(UUID id, @Valid DictionaryRequest request) {
        Optional<Dictionary> optionalItem = dictionaryItemMapper.findById(id);

        Dictionary item = dictionaryMapstruct.toModel(request);
        item.setId(id);

        if (optionalItem.isPresent()) {
            dictionaryItemMapper.update(item);
        } else {
            item.setIsActive(true);
            item.setDeletedAt(null);
            dictionaryItemMapper.insert(item);
        }
        log.info("Dictionary with ID '{}' successfully created or updated.", id);
    }

    public List<DictionaryResponse> filter(DictionaryRequest dictionaryRequest) {
        return dictionaryItemMapper
                .filter(dictionaryRequest)
                .stream()
                .map(dictionaryMapstruct::toResponse)
                .collect(Collectors.toList());
    }

    public DictionaryResponse getById(UUID id) {
        Dictionary item = dictionaryItemMapper.findById(id)
                .orElseThrow(NotFoundException::new);
        return dictionaryMapstruct.toResponse(item);
    }


    public void restore(UUID id) {
        Dictionary item = dictionaryItemMapper.findById(id)
                .orElseThrow(NotFoundException::new);

        if (Boolean.TRUE.equals(item.getIsActive())) {
            throw new AlreadyActivesException();
        }

        dictionaryItemMapper.restore(id);
    }
}
