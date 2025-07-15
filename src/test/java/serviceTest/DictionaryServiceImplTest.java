package serviceTest;

import az.cybernet.internship.dictionary.dto.DictionaryRequest;
import az.cybernet.internship.dictionary.dto.DictionaryResponse;
import az.cybernet.internship.dictionary.exception.AlreadyActiveException;
import az.cybernet.internship.dictionary.exception.DictionaryNotFoundException;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.model.Dictionary;
import az.cybernet.internship.dictionary.service.impl.DictionaryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DictionaryServiceImplTest {

    @Mock
    DictionaryMapper mapper;

    @InjectMocks
    DictionaryServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllActiveDictionaryWithLimit_ReturnsList() {
        Dictionary dict = new Dictionary();
        dict.setId(UUID.randomUUID());
        dict.setCategory("Test Category");
        dict.setValue("Test Value");
        dict.setDescription("Test Description");

        when(mapper.findAllActiveDictionaryWithLimit("Test", true, 5))
                .thenReturn(List.of(dict));

        List<DictionaryResponse> result = service.getAllActiveDictionaryWithLimit("Test", true, 5);

        assertEquals(1, result.size());
        assertEquals(dict.getCategory(), result.get(0).getCategory());
        verify(mapper, times(1)).findAllActiveDictionaryWithLimit("Test", true, 5);
    }

    @Test
    void testGetAllActiveDictionaryWithLimit_ThrowsExceptionWhenEmpty() {
        when(mapper.findAllActiveDictionaryWithLimit("Test", true, 5))
                .thenReturn(Collections.emptyList());

        DictionaryNotFoundException thrown = assertThrows(
                DictionaryNotFoundException.class,
                () -> service.getAllActiveDictionaryWithLimit("Test", true, 5)
        );

        assertEquals("Dictionary not found", thrown.getMessage());
        verify(mapper, times(1)).findAllActiveDictionaryWithLimit("Test", true, 5);
    }

    @Test
    void restoreDictionary_Success() {
        UUID id = UUID.randomUUID();
        Dictionary dict = new Dictionary();
        dict.setId(id);
        dict.setCategory("Category");
        dict.setValue("Value");
        dict.setDescription("Description");
        dict.setIsActive(false);

        when(mapper.findById(id)).thenReturn(dict);

        DictionaryResponse response = service.restoreDictionary(id);

        assertEquals(id, response.getId());
        assertEquals("Category", response.getCategory());
        verify(mapper).restore(id);
    }

    @Test
    void restoreDictionary_NotFound_ThrowsException() {
        UUID id = UUID.randomUUID();
        when(mapper.findById(id)).thenReturn(null);

        DictionaryNotFoundException exception = assertThrows(DictionaryNotFoundException.class, () ->
                service.restoreDictionary(id));

        assertEquals("Dictionary not found", exception.getMessage());
        verify(mapper, never()).restore(any());
    }

    @Test
    void restoreDictionary_AlreadyActive_ThrowsException() {
        UUID id = UUID.randomUUID();
        Dictionary dict = new Dictionary();
        dict.setId(id);
        dict.setIsActive(true);

        when(mapper.findById(id)).thenReturn(dict);

        AlreadyActiveException exception = assertThrows(AlreadyActiveException.class, () ->
                service.restoreDictionary(id));

        assertEquals("Entry is already active", exception.getMessage());
        verify(mapper, never()).restore(any());
    }

    @Test
    void updateOrInsert_UpdateExisting() {
        UUID id = UUID.randomUUID();

        DictionaryRequest request = new DictionaryRequest();
        request.setId(id);
        request.setCategory("Updated");
        request.setValue("UpdatedValue");
        request.setDescription("UpdatedDesc");
        request.setIsActive(true);
        request.setDeletedAt(null);

        Dictionary existing = new Dictionary();
        existing.setId(id);
        existing.setCategory("Old");
        existing.setValue("OldValue");
        existing.setDescription("OldDesc");
        existing.setIsActive(false);

        when(mapper.findById(id)).thenReturn(existing);

        DictionaryResponse response = service.updateOrInsert(request);

        assertEquals("Updated", response.getCategory());
        assertEquals("UpdatedValue", response.getValue());

        verify(mapper).update(existing);
        verify(mapper, never()).insert(any());
    }

    @Test
    void updateOrInsert_InsertNew() {
        UUID id = UUID.randomUUID();

        DictionaryRequest request = new DictionaryRequest();
        request.setId(id);
        request.setCategory("New");
        request.setValue("NewValue");
        request.setDescription("NewDesc");
        request.setIsActive(true);
        request.setDeletedAt(null);

        when(mapper.findById(id)).thenReturn(null);

        DictionaryResponse response = service.updateOrInsert(request);

        assertEquals("New", response.getCategory());
        assertEquals("NewValue", response.getValue());

        // mapper.insert çağırılmalıdı
        verify(mapper).insert(any(Dictionary.class));
        verify(mapper, never()).update(any());
    }

    @Test
    void softDelete_Success() {
        UUID id = UUID.randomUUID();
        Dictionary dictionary = new Dictionary();
        dictionary.setId(id);
        dictionary.setCategory("TestCategory");
        dictionary.setValue("TestValue");
        dictionary.setDescription("TestDescription");
        dictionary.setIsActive(true);
        dictionary.setDeletedAt(null);

        when(mapper.findById(id)).thenReturn(dictionary);

        DictionaryResponse response = service.softDelete(id);

        assertEquals(id, response.getId());
        assertEquals("TestCategory", response.getCategory());
        assertEquals("TestValue", response.getValue());
        assertEquals("TestDescription", response.getDescription());

        verify(mapper).updateForSoftDelete(id);
    }

    @Test
    void softDelete_DictionaryNotFound() {
        UUID id = UUID.randomUUID();
        when(mapper.findById(id)).thenReturn(null);

        DictionaryNotFoundException exception = assertThrows(DictionaryNotFoundException.class, () ->
                service.softDelete(id));

        assertEquals("Dictionary not found", exception.getMessage());
        verify(mapper, never()).updateForSoftDelete(any());
    }
}