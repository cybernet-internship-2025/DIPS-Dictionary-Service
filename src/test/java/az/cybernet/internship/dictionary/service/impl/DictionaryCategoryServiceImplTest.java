package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.converter.DictionaryCategoryConverter;
import az.cybernet.internship.dictionary.dto.request.DictionaryCategoryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryCategoryResponseDTO;
import az.cybernet.internship.dictionary.mapper.DictionaryCategoryMapper;
import az.cybernet.internship.dictionary.model.DictionaryCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DictionaryCategoryServiceImplTest {
    private DictionaryCategoryMapper mapper;
    private DictionaryCategoryConverter converter;
    private DictionaryCategoryServiceImpl service;

    @BeforeEach
    void setUp() {
        mapper = mock(DictionaryCategoryMapper.class);
        converter = mock(DictionaryCategoryConverter.class);
        service = new DictionaryCategoryServiceImpl(mapper, converter);
    }

    @Test
    void testSelectAll() {
        DictionaryCategory category = new DictionaryCategory();
        DictionaryCategoryResponseDTO response = new DictionaryCategoryResponseDTO();

        when(mapper.selectAll()).thenReturn(List.of(category));
        when(converter.convert(category)).thenReturn(response);

        List<DictionaryCategoryResponseDTO> result = service.selectAll();

        assertEquals(1, result.size());
        verify(mapper, times(1)).selectAll();
    }

    @Test
    void testInsert() {
        DictionaryCategoryRequestDTO request = new DictionaryCategoryRequestDTO();
        request.setName("Tech");

        when(mapper.selectByName("Tech")).thenReturn(null);

        DictionaryCategory entity = new DictionaryCategory();
        when(converter.convert(request)).thenReturn(entity);
        when(converter.convert(any(DictionaryCategory.class))).thenReturn(new DictionaryCategoryResponseDTO());

        DictionaryCategoryResponseDTO result = service.insert(request);

        assertNotNull(result);
        verify(mapper, times(1)).insert(any(DictionaryCategory.class));
    }
}
