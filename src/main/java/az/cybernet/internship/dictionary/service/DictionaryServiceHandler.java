package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dao.entity.DictionaryEntity;
import az.cybernet.internship.dictionary.dao.repository.DictionaryRepository;

import az.cybernet.internship.dictionary.exeption.DictionaryNotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.model.enums.DictionaryStatus;
import az.cybernet.internship.dictionary.model.request.CreateDictionaryRequest;
import az.cybernet.internship.dictionary.model.request.UpdateDescriptionRequest;
import az.cybernet.internship.dictionary.model.response.DictionaryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class DictionaryServiceHandler implements DictionaryService {

    private final DictionaryRepository dictionaryRepository;
    private final DictionaryMapper dictionaryMapper;

    @Override
    @Transactional(readOnly = true)
    public DictionaryResponse getDictionary(Long id) {
        var entity = fetchActiveDictionary(id);
        return dictionaryMapper.toResponse(entity);
    }

    @Transactional
    public void saveDictionary(CreateDictionaryRequest request) {
        var entity = DictionaryEntity.builder()
                .value(request.getValue())
                .description(request.getDescription())
                .status(DictionaryStatus.ACTIVE)
                .build();

        dictionaryRepository.save(entity);
        log.info("Dictionary created with id: {}", entity.getId());
    }

    @Override
    @Transactional
    public void updateDescription(Long id, UpdateDescriptionRequest request) {
        var entity = fetchActiveDictionary(id);
        entity.setDescription(request.getDescription());
        dictionaryRepository.save(entity);
        log.info("Updated description for id: {}", id);
    }

    @Override
    @Transactional
    public void deleteDictionary(Long id) {
        var entity = fetchActiveDictionary(id);
        entity.setStatus(DictionaryStatus.DELETED);
        entity.setDeletedAt(LocalDateTime.now());
        dictionaryRepository.save(entity);
        log.info("Deleted dictionary with id: {}", id);
    }

    @Transactional
    public void restoreDictionary(Long id) {
        var entity = dictionaryRepository.findById(id)
                .orElseThrow(() -> new DictionaryNotFoundException("Dictionary with id: " + id + " not found"));

        if (entity.getStatus() == DictionaryStatus.ACTIVE) {
            throw new IllegalStateException("Dictionary is already active.");
        }

        entity.setStatus(DictionaryStatus.ACTIVE);
        entity.setDeletedAt(null);
        dictionaryRepository.save(entity);
        log.info("Restored dictionary with id: {}", id);
    }

    private DictionaryEntity fetchActiveDictionary(Long id) {
        return dictionaryRepository.findByIdAndStatusNot(id, DictionaryStatus.DELETED)
                .orElseThrow(() -> new DictionaryNotFoundException("Dictionary with id: " + id + " not found"));
    }
}