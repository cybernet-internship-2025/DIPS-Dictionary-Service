package az.cybernet.internship.dictionary.service.concrete;

import az.cybernet.internship.dictionary.entity.DictionaryCategory;
import az.cybernet.internship.dictionary.exception.NotFoundException;
import az.cybernet.internship.dictionary.model.request.CategoryRequest;
import az.cybernet.internship.dictionary.model.response.CategoryResponse;
import az.cybernet.internship.dictionary.repository.CategoryMapper;
import az.cybernet.internship.dictionary.service.abstraction.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static az.cybernet.internship.dictionary.exception.ExceptionConstants.CATEGORY_NOT_FOUND;
import static az.cybernet.internship.dictionary.mapper.CategoryMapper.CATEGORY_MAPPER;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
    CategoryMapper categoryMapper;

    @Override
    public void upsert(CategoryRequest request) {
        log.info("ActionLog.createAndUpdateCategory.start - request: {}", request);
        if (request.getId() != null) {
            var dictionaryCategory = fetchDictionaryIfExist(request.getId());
        }

        var dictionaryCategory = CATEGORY_MAPPER.buildDictionaryCategory(request);
        categoryMapper.upsert(dictionaryCategory);
        log.info("ActionLog.createAndUpdateCategory.end - request: {}", request);
    }

    @Override
    public CategoryResponse findById(Long id) {
        log.info("ActionLog.findByIdCategory.start - id: {}", id);
        var dictionaryCategory = fetchDictionaryIfExist(id);
        var categoryResponse = CATEGORY_MAPPER.buildCategoryResponse(dictionaryCategory);
        log.info("ActionLog.findByIdCategory.end - id: {}, response: {}", id, categoryResponse);
        return categoryResponse;
    }

    @Override
    public void deleteCategory(Long id) {
        log.info("ActionLog.deleteCategory.start - id: {}", id);
        var dictionaryCategory = fetchDictionaryIfExist(id);
        categoryMapper.deleteCategory(dictionaryCategory.getId());
        log.info("ActionLog.deleteCategory.end - id: {}", id);
    }

    private DictionaryCategory fetchDictionaryIfExist(Long id) {
        var category = categoryMapper.findById(id);

        if (category == null) {
            throw new NotFoundException(CATEGORY_NOT_FOUND.getCode(), CATEGORY_NOT_FOUND.getMessage(id));
        }

        return category;
    }
}
