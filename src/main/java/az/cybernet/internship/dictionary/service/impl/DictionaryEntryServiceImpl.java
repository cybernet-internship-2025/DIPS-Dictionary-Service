package az.cybernet.internship.dictionary.service.impl;

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

    @Override
    public List<DictionaryEntry> selectAll() {
        return dictionaryEntryMapper.selectAll();
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
    public void insert(DictionaryEntry entry) {
        dictionaryEntryMapper.insert(entry);
    }

    @Override
    public void update(DictionaryEntry entry) {
        if (dictionaryEntryMapper.selectById(entry.getId()) == null) {
            throw new NotFoundException("Entry not found");
        }
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
