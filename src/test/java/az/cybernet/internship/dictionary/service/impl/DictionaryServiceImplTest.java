package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.dto.req.DictionaryReq;
import az.cybernet.internship.dictionary.dto.resp.DictionaryResp;
import az.cybernet.internship.dictionary.entity.Dictionary;
import az.cybernet.internship.dictionary.exception.DictionaryNotFoundException;
import az.cybernet.internship.dictionary.exception.InputValueMissingException;
import az.cybernet.internship.dictionary.mapper.DictionaryMapper;
import az.cybernet.internship.dictionary.mapstruct.DictionaryMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DictionaryServiceImplTest {
    @Mock
    private DictionaryMapper dictionaryMapper;

    @Mock
    private DictionaryMap dictionaryMap;

    @InjectMocks
    private DictionaryServiceImpl dictionaryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findDictionaries_success() {
        UUID id = UUID.randomUUID();
        List<Dictionary> mockEntityList = List.of(new Dictionary());
        List<DictionaryResp> mockRespList = List.of(new DictionaryResp());

        when(dictionaryMapper.findByFilters(id, null, true, 10)).thenReturn(mockEntityList);
        when(dictionaryMap.toDto(mockEntityList)).thenReturn(mockRespList);

        List<DictionaryResp> result = dictionaryService.findDictionaries(id, null, true, 10);

        assertEquals(1, result.size());
        verify(dictionaryMapper).findByFilters(id, null, true, 10);
        verify(dictionaryMap).toDto(mockEntityList);
    }

    @Test
    void findDictionaries_notFound_throwsException() {
        UUID id = UUID.randomUUID();
        when(dictionaryMapper.findByFilters(id, null, true, 10)).thenReturn(Collections.emptyList());

        assertThrows(DictionaryNotFoundException.class, () ->
                dictionaryService.findDictionaries(id, null, true, 10));
    }


    @Test
    void updateDictionary_success() {
        //this seems so fckd up. forgive me god.
        DictionaryReq dictionary = new DictionaryReq();
        dictionary.setId(UUID.randomUUID().toString());
        dictionary.setValue("test");

        when(dictionaryMap.toEntity(dictionary)).thenReturn(new Dictionary());
        when(dictionaryMapper.updateDictionary(any(Dictionary.class))).thenReturn(new Dictionary());
        when(dictionaryMap.toDto(any(Dictionary.class))).thenReturn(new DictionaryResp());

        DictionaryResp result = dictionaryService.updateDictionary(dictionary);

        assertNotNull(result);
        verify(dictionaryMapper).updateDictionary(dictionaryMap.toEntity(dictionary));
    }

    @Test
    void updateDictionary_missingInput_throwsException() {
        DictionaryReq dictionary = new DictionaryReq();
        dictionary.setValue("value");

        assertThrows(InputValueMissingException.class, () ->
                dictionaryService.updateDictionary(dictionary));
    }

    @Test
    void updateDictionary_notFound_throwsException() {
        DictionaryReq dictionary = new DictionaryReq();
        dictionary.setId(UUID.randomUUID().toString());
        dictionary.setValue("value");

        when(dictionaryMapper.updateDictionary(any(Dictionary.class))).thenReturn(null);

        assertThrows(DictionaryNotFoundException.class, () ->
                dictionaryService.updateDictionary(dictionary));
    }

    @Test
    void delete_success() {
        UUID id = UUID.randomUUID();
        Dictionary mockDictionary = new Dictionary();

        when(dictionaryMapper.delete(id)).thenReturn(mockDictionary);
        when(dictionaryMap.toDto(any(Dictionary.class))).thenReturn(new DictionaryResp());

        DictionaryResp result = dictionaryService.delete(id);

        assertNotNull(result);
        verify(dictionaryMapper).delete(id);
    }

    @Test
    void delete_notFound_throwsException() {
        UUID id = UUID.randomUUID();
        when(dictionaryMapper.delete(id)).thenReturn(null);

        assertThrows(DictionaryNotFoundException.class, () ->
                dictionaryService.delete(id));
    }

    @Test
    void restoreDictionary_success() {
        UUID id = UUID.randomUUID();

        when(dictionaryMapper.restoreDictionary(id)).thenReturn(new Dictionary());
        when(dictionaryMap.toDto(any(Dictionary.class))).thenReturn(new DictionaryResp());

        DictionaryResp response = dictionaryService.restoreDictionary(id);
        assertNotNull(response);
        verify(dictionaryMapper).restoreDictionary(id);
    }

    @Test
    void restoreDictionary_notFound_trowsException() {
        UUID id = UUID.randomUUID();

        when(dictionaryMapper.restoreDictionary(id)).thenReturn(null);

        assertThrows(DictionaryNotFoundException.class, () -> dictionaryService.restoreDictionary(id));
    }
}
