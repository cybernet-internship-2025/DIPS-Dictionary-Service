package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.converter.DictionaryEntryConverter;
import az.cybernet.internship.dictionary.dto.request.DictionaryEntryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryEntryResponseDTO;
import az.cybernet.internship.dictionary.exception.model.ConflictException;
import az.cybernet.internship.dictionary.exception.type.ExceptionType;
import az.cybernet.internship.dictionary.exception.model.NotFoundException;
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
    public DictionaryEntryResponseDTO insert(DictionaryEntryRequestDTO entryRequestDTO) {
        DictionaryEntry entry = dictionaryEntryConverter.convert(entryRequestDTO);

        entry.setId(UUID.randomUUID());
        dictionaryEntryMapper.insert(entry);

        return dictionaryEntryConverter.convert(entry);
    }

    @Override
    public DictionaryEntryResponseDTO update(DictionaryEntryRequestDTO entryRequestDTO) {
        DictionaryEntry entry = selectEntryOrThrow(entryRequestDTO.getId());

        dictionaryEntryConverter.updateEntryFromDto(entryRequestDTO, entry);
        dictionaryEntryMapper.update(entry);

        return dictionaryEntryConverter.convert(entry);
    }

    @Override
    public void delete(UUID id) {
        DictionaryEntry entry = selectEntryOrThrow(id);

        entry.setActive(false);
        entry.setDeletedAt(LocalDateTime.now());

        dictionaryEntryMapper.delete(entry);
    }

    public DictionaryEntryResponseDTO restore(UUID id) {
        DictionaryEntry entry = selectEntryOrThrow(id);

        if (entry.isActive()) {
            throw new ConflictException(ExceptionType.ENTRY_ALREADY_ACTIVE);
        }

        entry.setActive(true);
        entry.setDeletedAt(null);
        dictionaryEntryMapper.update(entry);

        return dictionaryEntryConverter.convert(entry);
    }

    private DictionaryEntry selectEntryOrThrow(UUID id) {
        DictionaryEntry entry = dictionaryEntryMapper.selectById(id);
        if (entry == null) {
            throw new NotFoundException(ExceptionType.ENTRY_NOT_FOUND);
        }
        return entry;
    }
}
