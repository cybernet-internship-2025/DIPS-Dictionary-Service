package az.cybernet.internship.dictionary.service.concrete;

import az.cybernet.internship.dictionary.entity.DictionaryCategory;
import az.cybernet.internship.dictionary.exception.NotFoundException;
import az.cybernet.internship.dictionary.mapper.CategoryMapper;
import az.cybernet.internship.dictionary.model.request.CategoryRequest;
import az.cybernet.internship.dictionary.model.response.CategoryResponse;
import az.cybernet.internship.dictionary.repository.CategoryRepository;
import az.cybernet.internship.dictionary.service.abstraction.CategoryService;
import az.cybernet.internship.dictionary.util.CacheUtil;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static az.cybernet.internship.dictionary.exception.ExceptionConstants.CATEGORY_NOT_FOUND;
import static java.time.temporal.ChronoUnit.HOURS;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    CacheUtil cacheUtil;


    @Override
    public void saveOrUpdateCategory(CategoryRequest request) {
        if (request.getId() == null) {
            log.info("ActionLog.saveCategory.start - request: {}", request);
            var dictionaryCategory = categoryMapper.buildDictionaryCategory(request);
            categoryRepository.saveCategory(dictionaryCategory);
            log.info("ActionLog.saveCategory.end - request: {}", request);
        } else {
            log.info("ActionLog.updateCategory.start - request: {}", request);
            var dictionaryCategory = fetchCategoryIfExist(request.getId());
            categoryMapper.updateCategory(dictionaryCategory, request);
            categoryRepository.updateCategory(dictionaryCategory);

            cacheUtil.delete("category:" + request.getId());
            cacheUtil.deleteByPrefix("category:all:");
            log.info("ActionLog.updateCategory.end - request: {}", request);
        }
    }

    @Override
    public void restoreCategory(Long id) {
        log.info("ActionLog.restoreCategory.start - id: {}", id);
        var dictionaryCategory = fetchCategoryIfExist(id);
        categoryRepository.restoreCategory(dictionaryCategory.getId());

        cacheUtil.delete("category:" + id);
        cacheUtil.deleteByPrefix("category:all:");
        log.info("ActionLog.restoreCategory.end - id: {}", id);
    }

    @Override
    public CategoryResponse findById(Long id) {
        log.info("ActionLog.findByIdCategory.start - id: {}", id);

        var cached = cacheUtil.getBucket("category:" + id);
        if (cached != null) {
            log.info("ActionLog.findByIdCategory.cached - id: {}", id);
            return (CategoryResponse) cached;
        }

        var dictionaryCategory = fetchCategoryIfExist(id);
        var categoryResponse = categoryMapper.buildCategoryResponse(dictionaryCategory);

        cacheUtil.saveToCache("category:" + id, categoryResponse, 1L, HOURS);

        log.info("ActionLog.findByIdCategory.end - id: {}, response: {}", id, categoryResponse);
        return categoryResponse;
    }

    @Override
    public List<CategoryResponse> findAll(Integer limit) {
        log.info("ActionLog.findAllCategories.start - limit: {}", limit);

        String cacheKeyPrefix = "category:all:" + (limit == null ? "all" : limit) + ":";
        List<CategoryResponse> result = new ArrayList<>();

        for (int i = 0; ; i++) {
            var item = cacheUtil.getBucket(cacheKeyPrefix + i);
            if (item == null) break;
            result.add((CategoryResponse) item);
        }

        if (!result.isEmpty()) {
            log.info("ActionLog.findAllCategories.cached - limit: {}", limit);
            return result;
        }

        var dictionaryCategories = limit == null || limit == 0
                ? categoryRepository.findAll()
                : categoryRepository.findAllWithLimit(limit);

        var list = dictionaryCategories.stream()
                .map(categoryMapper::buildCategoryResponse)
                .toList();

        for (int i = 0; i < list.size(); i++) {
            cacheUtil.saveToCache(cacheKeyPrefix + i, list.get(i), 1L, HOURS);
        }

        log.info("ActionLog.findAllCategories.end - limit: {}", limit);
        return list;
    }

    @Override
    public void deleteCategory(Long id) {
        log.info("ActionLog.deleteCategory.start - id: {}", id);
        var dictionaryCategory = fetchCategoryIfExist(id);
        categoryRepository.deleteCategory(dictionaryCategory.getId());

        cacheUtil.delete("category:" + id);
        cacheUtil.deleteByPrefix("category:all:");
        log.info("ActionLog.deleteCategory.end - id: {}", id);
    }

    @Override
    public DictionaryCategory fetchCategoryIfExist(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException(CATEGORY_NOT_FOUND.getCode(), CATEGORY_NOT_FOUND.getMessage(id)));
    }
}
