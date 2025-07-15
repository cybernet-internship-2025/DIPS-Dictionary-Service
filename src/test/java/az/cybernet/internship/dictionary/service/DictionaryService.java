package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.exception.BadRequestException;
import az.cybernet.internship.dictionary.exception.ResourceNotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryServiceMapper;
import az.cybernet.internship.dictionary.model.DictionaryEntry;
import az.cybernet.internship.dictionary.model.dto.DictionaryCreateUpdateDto;
import az.cybernet.internship.dictionary.model.dto.DictionaryResponseDto;
import az.cybernet.internship.dictionary.repository.DictionaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final DictionaryMapper dictionaryMapper;
    private final DictionaryServiceMapper dictionaryServiceMapper; // Added MapStruct dependency


    public List<DictionaryResponseDto> retrieveDictionaries(
            UUID id,
            String value,
            Boolean isActive,
            Integer limit) {

        List<DictionaryEntry> entries = dictionaryMapper.findDictionaries(id, value, isActive, limit);

        return dictionaryServiceMapper.toDtoList(entries);
    }

    @Transactional // Ensures the entire method runs in a single database transaction
    public DictionaryResponseDto createOrUpdateDictionary(DictionaryCreateUpdateDto dto) {
        if (dto.getId() == null) {
            // --- CREATE path ---
            return createNewEntry(dto);
        } else {
            // --- UPDATE path ---
            return updateExistingEntry(dto);
        }
    }

    private DictionaryResponseDto createNewEntry(DictionaryCreateUpdateDto dto) {
        // Prevent creating an entry with a value that already exists
        dictionaryMapper.findByValue(dto.getValue()).ifPresent(entry -> {
            throw new BadRequestException("Dictionary entry with value '" + dto.getValue() + "' already exists.");
        });

        DictionaryEntry newEntry = dictionaryServiceMapper.toEntity(dto);
        newEntry.setId(UUID.randomUUID());
        newEntry.setIsActive(true); // New entries are active by default
        newEntry.setCreatedAt(OffsetDateTime.now());

        dictionaryMapper.insert(newEntry);

        return dictionaryServiceMapper.toDto(newEntry);
    }

    private DictionaryResponseDto updateExistingEntry(DictionaryCreateUpdateDto dto) {
        // Find the existing entry or throw a 404 Not Found exception
        DictionaryEntry existingEntry = dictionaryMapper.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No dictionary entry found with ID: " + dto.getId()));

        // Use MapStruct to update the entity's fields from the DTO
        dictionaryServiceMapper.updateEntityFromDto(dto, existingEntry);
        existingEntry.setUpdatedAt(OffsetDateTime.now());

        dictionaryMapper.update(existingEntry);

        return dictionaryServiceMapper.toDto(existingEntry);
    }

    @Transactional
    public void softDeleteDictionary(UUID id) {
        // 1. Find the entry or throw an exception if it doesn't exist.
        DictionaryEntry entry = dictionaryMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot delete. No dictionary entry found with ID: " + id));

        // 2. Set the timestamps for the soft delete operation.
        OffsetDateTime now = OffsetDateTime.now();

        // 3. Call the mapper method to perform the update.
        dictionaryMapper.softDelete(id, now, now); // Passing 'now' for both deleted_at and updated_at
    }

    /**
     * Restores a soft-deleted dictionary entry.
     *
     * @return The restored dictionary entry.
     */
    @Transactional
    public DictionaryResponseDto restoreDictionary(UUID id) {
        // 1. Find the entry by its ID. If it's not found, throw a 404 error.
        DictionaryEntry entry = dictionaryMapper.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot restore. No dictionary entry found with ID: " + id));

        // 2. Check if the entry is already active. If so, throw a 400 error.
        if (entry.getIsActive()) {
            throw new BadRequestException("Cannot restore. The dictionary entry with ID " + id + " is already active.");
        }

        // 3. If the entry exists and is inactive, proceed with restoring it.
        OffsetDateTime now = OffsetDateTime.now();
        dictionaryMapper.restore(id, now);

        // 4. Fetch the updated entry from the database to return the latest state.
        DictionaryEntry restoredEntry = dictionaryMapper.findById(id).get(); // .get() is safe here because we know it exists.

        // 5. Map it to a DTO and return it.
        return dictionaryServiceMapper.toDto(restoredEntry);
    }

}