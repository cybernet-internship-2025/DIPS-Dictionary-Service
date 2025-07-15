package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.model.DictionaryEntry;
import az.cybernet.internship.dictionary.repository.DictionaryRepository;
import exception.DictionaryEntryNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static az.cybernet.internship.dictionary.enums.Mapper.DICTIONARY_MAPPER;

@Service
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryRepository dictionaryRepository;

//    public DictionaryService(DictionaryRepository dictionaryRepository) {
//        this.dictionaryRepository = dictionaryRepository;
//    }

    public List<DictionaryResponse> findAll() {
        List<DictionaryEntry> dictionaryEntries = dictionaryRepository.findAll();
        return dictionaryEntries.stream()
                .map(DICTIONARY_MAPPER::mapToResponse)
                .collect(Collectors.toList());


    }
    public DictionaryResponse findById(Long id) {
       Optional<DictionaryEntry> optional = dictionaryRepository.findById(id);
       if (optional.isPresent()) {
           return DICTIONARY_MAPPER.mapToResponse(optional.get());
       }
        throw  new DictionaryEntryNotFound("Dictionary entry not found");
    }
    public DictionaryResponse saveOrUpdate(DictionaryRequest dictionaryRequest) {
        if (dictionaryRequest.getId() != null) {
            DictionaryEntry dictionaryEntry = dictionaryRepository.findById(dictionaryRequest.getId()).orElseThrow(() -> new DictionaryEntryNotFound(""));
            DICTIONARY_MAPPER.updateResponse(dictionaryEntry, dictionaryRequest);
            dictionaryRepository.update(dictionaryEntry);
            return DICTIONARY_MAPPER.mapToResponse(dictionaryEntry);
        }
        else {
            DictionaryEntry dictionaryEntry = DICTIONARY_MAPPER.buildEntry(dictionaryRequest);
            dictionaryRepository.insert(dictionaryEntry);
            return DICTIONARY_MAPPER.mapToResponse(dictionaryEntry);
        }


    }
    public void deleteById(Long id) {
        Optional<DictionaryEntry> optional=dictionaryRepository.findById(id);
        if (optional.isPresent()) {
            DictionaryEntry dictionaryEntry=optional.get();
            dictionaryEntry.setIsActive(false);
            dictionaryEntry.setUpdatedAt(LocalDateTime.now());
            dictionaryRepository.update(dictionaryEntry);
        }
    }
    public void restore(Long id) {
        Optional<DictionaryEntry> optional=dictionaryRepository.findById(id);
        if (optional.isPresent()) {
            DictionaryEntry dictionaryEntry=optional.get();
            dictionaryEntry.setIsActive(true);
        }
    }
}
