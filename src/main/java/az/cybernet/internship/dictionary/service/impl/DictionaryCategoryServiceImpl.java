package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.converter.DictionaryCategoryConverter;
import az.cybernet.internship.dictionary.dto.request.DictionaryCategoryRequestDTO;
import az.cybernet.internship.dictionary.dto.response.DictionaryCategoryResponseDTO;
import az.cybernet.internship.dictionary.exception.model.AlreadyExistsException;
import az.cybernet.internship.dictionary.exception.model.NotFoundException;
import az.cybernet.internship.dictionary.exception.type.ExceptionType;
import az.cybernet.internship.dictionary.mapper.DictionaryCategoryMapper;
import az.cybernet.internship.dictionary.model.DictionaryCategory;
import az.cybernet.internship.dictionary.service.DictionaryCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DictionaryCategoryServiceImpl implements DictionaryCategoryService {
    private final DictionaryCategoryMapper dictionaryCategoryMapper;
    private final DictionaryCategoryConverter dictionaryCategoryConverter;

    @Override
    @Cacheable(value = "category_cache", key = "'all_categories'")
    public List<DictionaryCategoryResponseDTO> selectAll() {
        return dictionaryCategoryMapper.selectAll()
                .stream()
                .map(dictionaryCategoryConverter::convert)
                .toList();
    }

    @Override
    @CacheEvict(value = "category_cache", key = "'all_categories'")
    public DictionaryCategoryResponseDTO insert(DictionaryCategoryRequestDTO categoryRequestDTO) {
        if (selectByName(categoryRequestDTO.getName()) != null) {
            throw new AlreadyExistsException(ExceptionType.CATEGORY_ALREADY_EXISTS);
        }

        DictionaryCategory category = dictionaryCategoryConverter.convert(categoryRequestDTO);

        category.setId(UUID.randomUUID().toString());
        dictionaryCategoryMapper.insert(category);

        return dictionaryCategoryConverter.convert(category);
    }

    @Override
    @CacheEvict(value = "category_cache", key = "'all_categories'")
    public DictionaryCategoryResponseDTO update(DictionaryCategoryRequestDTO categoryRequestDTO) {
        DictionaryCategory category = selectByID(categoryRequestDTO.getId());
        category.setName(categoryRequestDTO.getName());
        dictionaryCategoryMapper.update(category);
        return dictionaryCategoryConverter.convert(category);
    }

    @Override
    @CacheEvict(value = "category_cache", key = "'all_categories'")
    public void delete(String id) {
        DictionaryCategory category = selectByID(id);
        dictionaryCategoryMapper.delete(id);
    }

    @Override
    public DictionaryCategory selectByName(String name) {
        DictionaryCategory category = dictionaryCategoryMapper.selectByName(name);
        if (category == null) {
            throw new NotFoundException(ExceptionType.CATEGORY_NOT_FOUND);
        }
        return category;
    }

    public DictionaryCategory selectByID(String id) {
        DictionaryCategory category = dictionaryCategoryMapper.selectById(id);
        if (category == null) {
            throw new NotFoundException(ExceptionType.CATEGORY_NOT_FOUND);
        }
        return category;
    }
}
