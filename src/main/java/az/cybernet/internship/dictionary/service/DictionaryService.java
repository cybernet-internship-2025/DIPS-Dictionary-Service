package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.model.DictionaryEntry;
import az.cybernet.internship.dictionary.repository.DictionaryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static az.cybernet.internship.dictionary.enums.Mapper.DICTIONARY_MAPPER;

@Service
public class DictionaryService {
    private final DictionaryRepository dictionaryRepository;


    public DictionaryService(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    public List<DictionaryResponse> findAll() {
        List<DictionaryEntry> dictionaryEntries = dictionaryRepository.findAll();
        return dictionaryEntries.stream()
                .map(DICTIONARY_MAPPER::mapToResponse)
                .collect(Collectors.toList());


    }
    public DictionaryResponse findById(Long id) {
        DictionaryEntry dictionaryEntry = dictionaryRepository.findById(id).orElse(null);
        return DICTIONARY_MAPPER.mapToResponse(dictionaryEntry);
    }
    public DictionaryResponse saveOrUpdate(DictionaryResponse dictionaryResponse) {
        Optional<DictionaryEntry> optional=dictionaryRepository.findById(dictionaryResponse.getId());
        if (optional.isPresent()) {
            DictionaryEntry dictionaryEntry=optional.get();
            dictionaryEntry.setCategory(dictionaryResponse.getCategory());
            dictionaryEntry.setValue(dictionaryResponse.getValue());
            dictionaryEntry.setDescription(dictionaryResponse.getDescription());
            dictionaryEntry.setIsActive(true);
            dictionaryEntry.setCreatedAt(dictionaryResponse.getCreatedAt());
            dictionaryEntry.setUpdatedAt(LocalDateTime.now());
            dictionaryRepository.update(dictionaryEntry);
            return dictionaryResponse;
        }
        else {
            DictionaryEntry dictionaryEntry=new DictionaryEntry();
            dictionaryEntry.setCategory(dictionaryResponse.getCategory());
            dictionaryEntry.setValue(dictionaryResponse.getValue());
            dictionaryEntry.setDescription(dictionaryResponse.getDescription());
            dictionaryEntry.setIsActive(true);
            dictionaryEntry.setCreatedAt(dictionaryResponse.getCreatedAt());
            dictionaryEntry.setUpdatedAt(LocalDateTime.now());
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
    public void restore(DictionaryResponse dictionaryResponse) {
        DictionaryEntry dictionaryEntry=dictionaryRepository.findById(dictionaryResponse.getId()).get();
        if(dictionaryEntry.getIsActive()){
            dictionaryEntry.setIsActive(false);
            dictionaryEntry.setUpdatedAt(LocalDateTime.now());
            dictionaryRepository.update(dictionaryEntry);
        }
        else {
            dictionaryEntry.setIsActive(true);
            dictionaryEntry.setUpdatedAt(LocalDateTime.now());
            dictionaryRepository.update(dictionaryEntry);
        }


    }
}
