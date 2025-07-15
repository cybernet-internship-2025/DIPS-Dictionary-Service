package java.az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.converter.DictionaryEntryConverter;
import az.cybernet.internship.dictionary.dto.request.DictionaryEntryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryEntryResponseDTO;
import az.cybernet.internship.dictionary.mapper.DictionaryCategoryMapper;
import az.cybernet.internship.dictionary.mapper.DictionaryEntryMapper;
import az.cybernet.internship.dictionary.model.DictionaryCategory;
import az.cybernet.internship.dictionary.model.DictionaryEntry;
import az.cybernet.internship.dictionary.service.DictionaryCategoryService;
import az.cybernet.internship.dictionary.service.impl.DictionaryEntryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DictionaryEntryServiceImplTest {

    private DictionaryEntryMapper entryMapper;
    private DictionaryEntryConverter entryConverter;
    private DictionaryCategoryService categoryService;
    private DictionaryCategoryMapper categoryMapper;
    private DictionaryEntryServiceImpl service;

    @BeforeEach
    void setUp() {
        entryMapper = mock(DictionaryEntryMapper.class);
        entryConverter = mock(DictionaryEntryConverter.class);
        categoryService = mock(DictionaryCategoryService.class);
        categoryMapper = mock(DictionaryCategoryMapper.class);

        service = new DictionaryEntryServiceImpl(entryMapper, entryConverter, categoryService, categoryMapper);
    }

    @Test
    void testSelectAll() {
        DictionaryEntry entry = new DictionaryEntry();
        entry.setActive(true);
        entry.setCategoryID("123");

        DictionaryCategory category = new DictionaryCategory();
        category.setName("Science");

        when(entryMapper.selectAll()).thenReturn(List.of(entry));
        when(categoryMapper.selectById("123")).thenReturn(category);
        when(entryConverter.convert(entry)).thenReturn(new DictionaryEntryResponseDTO());

        List<DictionaryEntryResponseDTO> result = service.selectAll();

        assertEquals(1, result.size());
        verify(entryMapper).selectAll();
    }

    @Test
    void testInsert() {
        DictionaryEntryRequestDTO request = new DictionaryEntryRequestDTO();
        request.setValue("gravity");
        request.setCategoryName("Science");

        when(entryMapper.selectByValue("gravity")).thenReturn(null);

        DictionaryEntry entity = new DictionaryEntry();
        DictionaryCategory category = new DictionaryCategory();
        category.setId("cat-1");

        when(entryConverter.convert(request)).thenReturn(entity);
        when(categoryService.selectByName("Science")).thenReturn(category);
        when(entryConverter.convert(entity)).thenReturn(new DictionaryEntryResponseDTO());

        DictionaryEntryResponseDTO result = service.insert(request);

        assertNotNull(result);
        verify(entryMapper).insert(any(DictionaryEntry.class));
    }
}

