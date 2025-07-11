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

import java.util.List;

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
    public void saveOrUpdateCategory(CategoryRequest request) {
        if (request.getId() == null) {
            log.info("ActionLog.saveCategory.start - request: {}", request);
            var dictionaryCategory = CATEGORY_MAPPER.buildDictionaryCategory(request);
            categoryMapper.saveCategory(dictionaryCategory);
            log.info("ActionLog.saveCategory.end - request: {}", request);
        } else {
            log.info("ActionLog.updateCategory.start - request: {}", request);
            var dictionaryCategory = fetchDictionaryIfExist(request.getId());
            CATEGORY_MAPPER.updateCategory(dictionaryCategory, request);
            categoryMapper.updateCategory(dictionaryCategory);
            log.info("ActionLog.updateCategory.end - request: {}", request);
        }
    }

    @Override
    public void restoreCategory(Long id) {
        log.info("ActionLog.restoreCategory.start - id: {}", id);
        categoryMapper.restoreCategory(id);
        log.info("ActionLog.restoreCategory.end - id: {}", id);
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
    public List<CategoryResponse> findAll(Integer limit) {
        log.info("ActionLog.findAllCategories.start");
        var dictionaryCategories = categoryMapper.findAll(limit);
        var list = dictionaryCategories
                .stream()
                .map(CATEGORY_MAPPER::buildCategoryResponse)
                .toList();
        log.info("ActionLog.findAllCategories.end");
        return list;
    }

    @Override
    public void deleteCategory(Long id) {
        log.info("ActionLog.deleteCategory.start - id: {}", id);
        var dictionaryCategory = fetchDictionaryIfExist(id);
        categoryMapper.deleteCategory(dictionaryCategory.getId());
        log.info("ActionLog.deleteCategory.end - id: {}", id);
    }

    private DictionaryCategory fetchDictionaryIfExist(Long id) {
        return categoryMapper.findById(id).orElseThrow(() ->
                new NotFoundException(CATEGORY_NOT_FOUND.getCode(), CATEGORY_NOT_FOUND.getMessage(id)));
    }
}
