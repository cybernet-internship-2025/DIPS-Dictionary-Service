package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.exception.BadRequestException;
import az.cybernet.internship.dictionary.exception.ResourceNotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryServiceMapper;
import az.cybernet.internship.dictionary.mapper.DictionaryServiceMapperImpl;
import az.cybernet.internship.dictionary.model.DictionaryEntry;
import az.cybernet.internship.dictionary.model.dto.DictionaryCreateUpdateDto;
import az.cybernet.internship.dictionary.model.dto.DictionaryResponseDto;
import az.cybernet.internship.dictionary.repository.DictionaryMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // This initializes Mockito for JUnit 5
class DictionaryServiceTest {

    // @Mock creates a fake implementation of this dependency.
    // We will control its behavior in our tests.
    @Mock
    private DictionaryMapper dictionaryMapper;

    // @Spy uses a REAL object but allows us to monitor its interactions.
    // We need a real MapStruct mapper to verify mapping logic.
    @Spy
    private DictionaryServiceMapper dictionaryServiceMapper = new DictionaryServiceMapperImpl();

    // @InjectMocks creates a REAL instance of DictionaryService and injects
    // the @Mock and @Spy objects into it automatically.
    @InjectMocks
    private DictionaryService dictionaryService;

    @Test
    @DisplayName("CreateOrUpdate should CREATE new entry when ID is null")
    void createOrUpdateDictionary_whenCreating_shouldSucceed() {
        // ARRANGE (Setup the test)
        var createDto = new DictionaryCreateUpdateDto();
        createDto.setValue("test_value");
        createDto.setDescription("test_description");

        // Tell our fake mapper: "when findByValue is called, return nothing (Optional.empty())"
        when(dictionaryMapper.findByValue("test_value")).thenReturn(Optional.empty());

        // ACT (Execute the method we are testing)
        DictionaryResponseDto result = dictionaryService.createOrUpdateDictionary(createDto);

        // ASSERT (Verify the outcome)
        // 1. Check the response DTO
        assertThat(result).isNotNull();
        assertThat(result.getValue()).isEqualTo("test_value");
        assertThat(result.getId()).isNotNull(); // The service should have generated an ID.

        // 2. Capture the entity that was passed to the database 'insert' method
        ArgumentCaptor<DictionaryEntry> entryCaptor = ArgumentCaptor.forClass(DictionaryEntry.class);
        verify(dictionaryMapper).insert(entryCaptor.capture());

        // 3. Check the captured entity to ensure the service set the defaults correctly
        DictionaryEntry capturedEntry = entryCaptor.getValue();
        assertThat(capturedEntry.getValue()).isEqualTo("test_value");
        assertThat(capturedEntry.getIsActive()).isTrue(); // New entries must be active.
    }

    @Test
    @DisplayName("CreateOrUpdate should UPDATE existing entry when ID is present")
    void createOrUpdateDictionary_whenUpdating_shouldSucceed() {
        // ARRANGE
        UUID existingId = UUID.randomUUID();
        var updateDto = new DictionaryCreateUpdateDto();
        updateDto.setId(existingId);
        updateDto.setValue("updated_value");

        var existingEntryInDb = new DictionaryEntry(existingId, "old_value", "old_desc", true, OffsetDateTime.now(), null, null);

        // Tell our fake mapper: "when findById is called with this ID, return our fake entry"
        when(dictionaryMapper.findById(existingId)).thenReturn(Optional.of(existingEntryInDb));

        // ACT
        dictionaryService.createOrUpdateDictionary(updateDto);

        // ASSERT
        // Verify that the 'update' method was called on our mapper, and not 'insert'.
        verify(dictionaryMapper).update(any(DictionaryEntry.class));
        verify(dictionaryMapper, never()).insert(any(DictionaryEntry.class));
    }

    @Test
    @DisplayName("Restore should throw BadRequestException if entry is already active")
    void restoreDictionary_whenAlreadyActive_shouldThrowException() {
        // ARRANGE
        UUID existingId = UUID.randomUUID();
        // Create an entry that is ALREADY active.
        var activeEntry = new DictionaryEntry(existingId, "some_value", null, true, OffsetDateTime.now(), null, null);

        // Tell the mock to return this active entry when searched.
        when(dictionaryMapper.findById(existingId)).thenReturn(Optional.of(activeEntry));

        // ACT & ASSERT
        // Assert that calling the method throws the specific exception we expect.
        assertThrows(BadRequestException.class, () -> {
            dictionaryService.restoreDictionary(existingId);
        });

        // Also verify that the 'restore' database method was NEVER actually called.
        verify(dictionaryMapper, never()).restore(any(), any());
    }

    @Test
    @DisplayName("Soft delete should throw ResourceNotFoundException if entry does not exist")
    void softDeleteDictionary_whenEntryNotFound_shouldThrowException() {
        // ARRANGE
        UUID nonExistentId = UUID.randomUUID();
        // Tell the mock to return nothing when findById is called.
        when(dictionaryMapper.findById(nonExistentId)).thenReturn(Optional.empty());

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            dictionaryService.softDeleteDictionary(nonExistentId);
        });
    }
}