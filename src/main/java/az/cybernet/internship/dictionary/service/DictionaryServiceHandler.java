package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dao.entity.DictionaryEntity;
import az.cybernet.internship.dictionary.dao.repository.DictionaryRepository;
import az.cybernet.internship.dictionary.model.enums.DictionaryStatus;
import az.cybernet.internship.dictionary.model.request.CreateDictionaryRequest;
import az.cybernet.internship.dictionary.model.request.UpdateDescriptionRequest;
import az.cybernet.internship.dictionary.model.response.DictionaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DictionaryServiceHandler implements DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    public DictionaryResponse getDictionary(Long id) {
        var dictionary = fetchDictionaryIfExists(id);
        return new DictionaryResponse(
                dictionary.getId(),
                dictionary.getValue(),
                dictionary.getDescription(),
                dictionary.getDeletedAt()
        );
    }

    public void saveDictionary(CreateDictionaryRequest request) {
        dictionaryRepository.save(DictionaryEntity.builder()
                .value(request.getValue())
                .description(request.getDescription())
                .status(DictionaryStatus.ACTIVE)
                .build());
    }

    public void updateDescription(Long id, UpdateDescriptionRequest request) {
        var dict = dictionaryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        dict.setDescription(request.getDescription());
        dictionaryRepository.save(dict);
    }

    public void deleteDictionary(Long id) {
        var dict = dictionaryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        dict.setStatus(DictionaryStatus.DELETED);
        dict.setDeletedAt(LocalDateTime.now());
        dictionaryRepository.save(dict);
    }

    public void restoreDictionary(Long id) {
        var dict = dictionaryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (dict.getStatus() == DictionaryStatus.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dictionary is already active");
        }

        dict.setStatus(DictionaryStatus.ACTIVE);
        dict.setDeletedAt(null);
        dictionaryRepository.save(dict);
    }

    private DictionaryEntity fetchDictionaryIfExists(Long id) {
        return dictionaryRepository.findByIdAndStatusNot(id, DictionaryStatus.DELETED)
                .orElseThrow(RuntimeException::new);
    }

}
