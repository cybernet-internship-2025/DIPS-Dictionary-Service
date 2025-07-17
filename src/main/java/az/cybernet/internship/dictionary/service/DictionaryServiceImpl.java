package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.mapper.MapStruct;
import az.cybernet.internship.dictionary.model.DictionaryEntry;
import az.cybernet.internship.dictionary.repository.DictionaryRepository;
import exception.DictionaryEntryNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    MapStruct mapStruct;

    public List<DictionaryResponse> findAll() {
        List<DictionaryEntry> dictionaryEntries = dictionaryRepository.findAll();
        return mapStruct.mapToResponseList(dictionaryEntries);

    }
    public DictionaryResponse findById(Long id) {
       Optional<DictionaryEntry> optional = dictionaryRepository.findById(id);
       if (optional.isPresent()) {
          return mapStruct.mapToResponse(optional.get());
       }
        throw  new DictionaryEntryNotFound("Dictionary entry not found");
    }
    @Transactional
    public DictionaryResponse saveOrUpdate(DictionaryRequest dictionaryRequest) {
        if (dictionaryRequest.getId() != null) {
            DictionaryEntry dictionaryEntry = dictionaryRepository.findById(dictionaryRequest.getId()).orElseThrow(() -> new DictionaryEntryNotFound(""));
            DICTIONARY_MAPPER.updateResponse(dictionaryEntry, dictionaryRequest);
            dictionaryRepository.update(dictionaryEntry);
            return mapStruct.mapToResponse(dictionaryEntry);
        }
        else {
            DictionaryEntry dictionaryEntry = mapStruct.buildEntry(dictionaryRequest);
            dictionaryRepository.insert(dictionaryEntry);
            return mapStruct.mapToResponse(dictionaryEntry);
        }


    }
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) throws Exception {
        Optional<DictionaryEntry> optional=dictionaryRepository.findById(id);
        if (optional.isPresent()) {
            DictionaryEntry dictionaryEntry=optional.get();
            dictionaryEntry.setIsActive(false);
            dictionaryEntry.setUpdatedAt(LocalDateTime.now());
            dictionaryRepository.update(dictionaryEntry);
        }
        throw new Exception("Something went wrong - rollback triggered");


    }
    public void restore(Long id) {
        Optional<DictionaryEntry> optional=dictionaryRepository.findById(id);
        if (optional.isPresent()) {
            DictionaryEntry dictionaryEntry=optional.get();
            dictionaryEntry.setIsActive(true);
        }
    }
}
