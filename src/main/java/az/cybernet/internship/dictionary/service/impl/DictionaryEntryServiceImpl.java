package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.converter.DictionaryEntryConverter;
import az.cybernet.internship.dictionary.dto.request.DictionaryEntryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryEntryResponseDTO;
import az.cybernet.internship.dictionary.exception.model.ConflictException;
import az.cybernet.internship.dictionary.exception.type.ExceptionType;
import az.cybernet.internship.dictionary.exception.model.NotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryCategoryMapper;
import az.cybernet.internship.dictionary.mapper.DictionaryEntryMapper;
import az.cybernet.internship.dictionary.model.DictionaryEntry;
import az.cybernet.internship.dictionary.service.DictionaryCategoryService;
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
    private final DictionaryCategoryService dictionaryCategoryService;
    private final DictionaryCategoryMapper dictionaryCategoryMapper;

    @Override
    public List<DictionaryEntryResponseDTO> selectAll() {
        return dictionaryEntryMapper.selectAll()
                .stream()
                .filter(DictionaryEntry::isActive)
                .map(dictionaryEntry -> {
                    String categoryName = dictionaryCategoryMapper.selectById(dictionaryEntry.getCategoryID()).getName();
                    DictionaryEntryResponseDTO dictionaryEntryResponseDTO = dictionaryEntryConverter.convert(dictionaryEntry);
                    dictionaryEntryResponseDTO.setCategoryName(categoryName);
                    return dictionaryEntryResponseDTO;
                })
                .toList();
    }

    @Override
    public DictionaryEntryResponseDTO insert(DictionaryEntryRequestDTO entryRequestDTO) {
        DictionaryEntry entry = dictionaryEntryConverter.convert(entryRequestDTO);
        String categoryID = dictionaryCategoryService.selectByName(entryRequestDTO.getCategoryName()).getId();

        entry.setId(UUID.randomUUID().toString());
        entry.setActive(true);
        entry.setCategoryID(categoryID);

        dictionaryEntryMapper.insert(entry);

        DictionaryEntryResponseDTO dictionaryEntryResponseDTO = dictionaryEntryConverter.convert(entry);
        dictionaryEntryResponseDTO.setCategoryName(entryRequestDTO.getCategoryName());

        return dictionaryEntryResponseDTO;
    }

    @Override
    public DictionaryEntryResponseDTO update(DictionaryEntryRequestDTO entryRequestDTO) {
        DictionaryEntry entry = selectByID(entryRequestDTO.getId());

        dictionaryEntryConverter.updateEntryFromDto(entryRequestDTO, entry);
        String categoryID = dictionaryCategoryMapper.selectByName(entryRequestDTO.getCategoryName()).getId();
        entry.setCategoryID(categoryID);
        dictionaryEntryMapper.update(entry);

        DictionaryEntryResponseDTO dictionaryEntryResponseDTO = dictionaryEntryConverter.convert(entry);
        String categoryName = dictionaryCategoryMapper.selectById(entry.getCategoryID()).getName();

        dictionaryEntryResponseDTO.setCategoryName(categoryName);
        return dictionaryEntryResponseDTO;
    }

    @Override
    public void delete(String id) {
        DictionaryEntry entry = selectByID(id);

        entry.setActive(false);
        entry.setDeletedAt(LocalDateTime.now());

        dictionaryEntryMapper.update(entry);
    }

    public DictionaryEntryResponseDTO restore(String id) {
        DictionaryEntry entry = selectByID(id);

        if (entry.isActive()) {
            throw new ConflictException(ExceptionType.ENTRY_ALREADY_ACTIVE);
        }

        entry.setActive(true);
        entry.setDeletedAt(null);
        dictionaryEntryMapper.update(entry);

        String categoryName = dictionaryCategoryMapper.selectById(entry.getCategoryID()).getName();
        DictionaryEntryResponseDTO dictionaryEntryResponseDTO = dictionaryEntryConverter.convert(entry);
        dictionaryEntryResponseDTO.setCategoryName(categoryName);

        return dictionaryEntryResponseDTO;
    }

    private DictionaryEntry selectByID(String id) {
        DictionaryEntry entry = dictionaryEntryMapper.selectById(id);
        if (entry == null) {
            throw new NotFoundException(ExceptionType.ENTRY_NOT_FOUND);
        }
        return entry;
    }
}
