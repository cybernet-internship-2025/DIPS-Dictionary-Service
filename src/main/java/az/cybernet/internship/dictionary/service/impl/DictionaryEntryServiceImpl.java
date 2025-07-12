package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.converter.DictionaryEntryConverter;
import az.cybernet.internship.dictionary.dto.request.DictionaryEntryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryEntryResponseDTO;
import az.cybernet.internship.dictionary.exception.NotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryEntryMapper;
import az.cybernet.internship.dictionary.model.DictionaryEntry;
import az.cybernet.internship.dictionary.service.DictionaryEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DictionaryEntryServiceImpl implements DictionaryEntryService {

    private final DictionaryEntryMapper dictionaryEntryMapper;
    private final DictionaryEntryConverter dictionaryEntryConverter;

    @Override
    public List<DictionaryEntryResponseDTO> selectAll() {
        return dictionaryEntryMapper.selectAll()
                .stream()
                .filter(DictionaryEntry::isActive)
                .map(dictionaryEntry -> dictionaryEntryConverter.convert(dictionaryEntry))
                .toList();
    }

    @Override
    public DictionaryEntry selectById(UUID id) {
        DictionaryEntry entry = dictionaryEntryMapper.selectById(id);
        if (entry == null) {
            throw new NotFoundException("Entry not found");
        }
        return entry;
    }

    @Override
    public void insert(DictionaryEntryRequestDTO entryRequestDTO) {
        DictionaryEntry entry = dictionaryEntryConverter.convert(entryRequestDTO);
        entry.setId(UUID.randomUUID());
        dictionaryEntryMapper.insert(entry);
    }

    @Override
    public void update(DictionaryEntryRequestDTO entryRequestDTO) {
        DictionaryEntry entry = dictionaryEntryMapper.selectById(entryRequestDTO.getId());
        if (entry == null) {
            throw new NotFoundException("Entry not found");
        }

        dictionaryEntryConverter.updateEntryFromDto(entryRequestDTO, entry);
        dictionaryEntryMapper.update(entry);
    }

    @Override
    public void delete(UUID id) {
        DictionaryEntry entry = dictionaryEntryMapper.selectById(id);
        if (entry == null) {
            throw new NotFoundException("Entry not found");
        }

        entry.setActive(false);
        entry.setDeletedAt(LocalDateTime.now());
        dictionaryEntryMapper.update(entry);
    }

    public void restore(UUID id) {
        DictionaryEntry entry = dictionaryEntryMapper.selectById(id);
        if (entry == null) {
            throw new NotFoundException("Entry not found");
        }

        if (entry.isActive()) {
            throw new IllegalStateException("Entry is already active");
        }

        entry.setActive(true);
        entry.setDeletedAt(null);
        dictionaryEntryMapper.update(entry);
    }
}
