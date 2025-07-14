package az.cybernet.internship.dictionary.service

import az.cybernet.internship.dictionary.entity.DictionaryCategory
import az.cybernet.internship.dictionary.exception.NotFoundException
import az.cybernet.internship.dictionary.mapper.CategoryMapper
import az.cybernet.internship.dictionary.model.request.CategoryRequest
import az.cybernet.internship.dictionary.model.response.CategoryResponse
import az.cybernet.internship.dictionary.repository.CategoryRepository
import az.cybernet.internship.dictionary.service.abstraction.CategoryService
import az.cybernet.internship.dictionary.service.concrete.CategoryServiceImpl
import az.cybernet.internship.dictionary.util.CacheUtil
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import java.time.LocalDateTime

import static java.time.temporal.ChronoUnit.HOURS

class CategoryServiceTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    CategoryService categoryService
    CategoryMapper categoryMapper
    CategoryRepository categoryRepository
    CacheUtil cacheUtil

    def setup() {
        categoryMapper = Mock()
        categoryRepository = Mock()
        cacheUtil = Mock()
        categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper, cacheUtil)
    }

    // saveOrUpdateCategory
    def "TestSaveOrUpdateCategory id is null case"() {
        given:
        def categoryRequest = new CategoryRequest(null, "TestName", "TestDescription")
        def dictionaryCategory = DictionaryCategory.builder()
                .id(1L)
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .build()

        categoryMapper.buildDictionaryCategory(categoryRequest) >> dictionaryCategory

        when:
        categoryService.saveOrUpdateCategory(categoryRequest)

        then:
        categoryRequest.name == dictionaryCategory.name
        categoryRequest.description == dictionaryCategory.description
        1 * categoryRepository.saveCategory(_ as DictionaryCategory)
    }

    def "TestSaveOrUpdateCategory id is not null success case"() {
        given:
        def categoryRequest = random.nextObject(CategoryRequest)
        def dictionaryCategory = DictionaryCategory.builder()
                .id(categoryRequest.id)
                .name(categoryRequest.name)
                .description(categoryRequest.description)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .build()

        when:
        categoryService.saveOrUpdateCategory(categoryRequest)
        categoryMapper.updateCategory(dictionaryCategory, categoryRequest)

        then:
        1 * categoryRepository.findById(categoryRequest.getId()) >> Optional.of(dictionaryCategory)
        1 * categoryRepository.updateCategory(dictionaryCategory)
        categoryRequest.id == dictionaryCategory.id
        categoryRequest.name == dictionaryCategory.name
        categoryRequest.description == dictionaryCategory.description
    }

    def "TestSaveOrUpdateCategory id is not null error case"() {
        given:
        def categoryRequest = random.nextObject(CategoryRequest)

        when:
        categoryService.saveOrUpdateCategory(categoryRequest)

        then:
        1 * categoryRepository.findById(categoryRequest.getId()) >> Optional.empty()
        NotFoundException ex = thrown()
        ex.getCode() == "CATEGORY_NOT_FOUND"
        ex.getMessage() == "No category with id (ID: %s) was found".formatted(categoryRequest.getId())
        0 * categoryRepository.updateCategory(_)
    }

    // restoreCategory
    def "TestRestoreCategory success case"() {
        given:
        def id = random.nextObject(Long)
        def dictionaryCategory = random.nextObject(DictionaryCategory)

        when:
        categoryService.restoreCategory(id)

        then:
        1 * categoryRepository.findById(id) >> Optional.of(dictionaryCategory)
        1 * categoryRepository.restoreCategory(dictionaryCategory.getId())
    }

    def "TestRestoreCategory NotFoundException case"() {
        given:
        def id = random.nextObject(Long)

        when:
        categoryService.restoreCategory(id)

        then:
        1 * categoryRepository.findById(id) >> Optional.empty()
        NotFoundException ex = thrown()
        ex.getCode() == "CATEGORY_NOT_FOUND"
        ex.getMessage() == "No category with id (ID: %s) was found".formatted(id)
        0 * categoryRepository.restoreCategory(_)
    }

    // findById
    def "TestFindById cache miss with DB fallback"() {
        given:
        def id = random.nextObject(Long)
        def dictionaryCategory = random.nextObject(DictionaryCategory)
        def categoryResponse = CategoryResponse.builder()
                .id(dictionaryCategory.id)
                .name(dictionaryCategory.name)
                .description(dictionaryCategory.description)
                .isActive(dictionaryCategory.isActive)
                .createdAt(dictionaryCategory.createdAt)
                .updatedAt(dictionaryCategory.updatedAt)
                .build()

        cacheUtil.getBucket("category:" + id) >> null
        categoryMapper.buildCategoryResponse(dictionaryCategory) >> categoryResponse

        when:
        def result = categoryService.findById(id)

        then:
        1 * cacheUtil.getBucket("category:" + id)
        1 * categoryRepository.findById(id) >> Optional.of(dictionaryCategory)
        1 * cacheUtil.saveToCache("category:" + id, categoryResponse, 1L, HOURS)
        result == categoryResponse
        result.id == dictionaryCategory.id
        result.name == dictionaryCategory.name
        result.description == dictionaryCategory.description
        result.isActive == dictionaryCategory.isActive
        result.createdAt == dictionaryCategory.createdAt
        result.updatedAt == dictionaryCategory.updatedAt
    }

    def "TestFindById cache hit case"() {
        given:
        def id = random.nextObject(Long)
        def cachedResponse = CategoryResponse.builder()
                .id(id)
                .name("Test Name")
                .description("Test Description")
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .build()


        cacheUtil.getBucket("category:" + id) >> cachedResponse

        when:
        def result = categoryService.findById(id)

        then:
        1 * cacheUtil.getBucket("category:" + id) >> cachedResponse
        0 * categoryRepository.findById(_)
        0 * cacheUtil.saveToCache(_, _, _, _)
        result == cachedResponse
        result.id == cachedResponse.id
        result.name == cachedResponse.name
        result.description == cachedResponse.description
        result.isActive == cachedResponse.isActive
        result.createdAt == cachedResponse.createdAt
        result.updatedAt == cachedResponse.updatedAt
    }


    def "TestFindById NotFoundException case"() {
        given:
        def id = random.nextObject(Long)
        cacheUtil.getBucket("category:" + id) >> null

        when:
        categoryService.findById(id)

        then:
        1 * cacheUtil.getBucket("category:" + id)
        1 * categoryRepository.findById(id) >> Optional.empty()
        NotFoundException ex = thrown()
        ex.code == "CATEGORY_NOT_FOUND"
        ex.message == "No category with id (ID: %s) was found".formatted(id)
    }

    // findAll
    def "TestFindAll cache miss with DB fallback"() {
        given:
        def limit = random.nextObject(Integer)
        def dictionaryCategory = random.nextObject(DictionaryCategory)
        def categoryResponse = CategoryResponse.builder()
                .id(dictionaryCategory.id)
                .name(dictionaryCategory.name)
                .description(dictionaryCategory.description)
                .isActive(dictionaryCategory.isActive)
                .createdAt(dictionaryCategory.createdAt)
                .updatedAt(dictionaryCategory.updatedAt)
                .build()

        cacheUtil.getBucket("category:all:$limit:0") >> null
        categoryMapper.buildCategoryResponse(dictionaryCategory) >> categoryResponse

        when:
        def categoryResponses = categoryService.findAll(limit)

        then:
        1 * cacheUtil.getBucket("category:all:$limit:0")
        1 * categoryRepository.findAll(limit) >> [dictionaryCategory]
        1 * cacheUtil.saveToCache("category:all:$limit:0", categoryResponse, 1L, HOURS)
        categoryResponse.id == dictionaryCategory.id
        categoryResponse.name == dictionaryCategory.name
        categoryResponse.description == dictionaryCategory.description
        categoryResponse.isActive == dictionaryCategory.isActive
        categoryResponse.createdAt == dictionaryCategory.createdAt
        categoryResponse.updatedAt == dictionaryCategory.updatedAt
        categoryResponses == [categoryResponse]
    }

    def "TestFindAll cache hit case"() {
        given:
        def limit = random.nextObject(Integer)
        def dictionaryCategory = random.nextObject(DictionaryCategory)
        def categoryResponse = CategoryResponse.builder()
                .id(dictionaryCategory.id)
                .name(dictionaryCategory.name)
                .description(dictionaryCategory.description)
                .isActive(dictionaryCategory.isActive)
                .createdAt(dictionaryCategory.createdAt)
                .updatedAt(dictionaryCategory.updatedAt)
                .build()

        cacheUtil.getBucket("category:all:$limit:0") >> categoryResponse

        when:
        def result = categoryService.findAll(limit)

        then:
        1 * cacheUtil.getBucket("category:all:$limit:0") >> categoryResponse
        0 * categoryRepository.findAll(_)
        0 * cacheUtil.saveToCache(_, _, _, _)
        result == [categoryResponse]
    }


    // deleteCategory
    def "TestDeleteCategory success case"() {
        given:
        def id = random.nextObject(Long)
        def dictionaryCategory = random.nextObject(DictionaryCategory)

        when:
        categoryService.deleteCategory(id)

        then:
        1 * categoryRepository.findById(id) >> Optional.of(dictionaryCategory)
        1 * categoryRepository.deleteCategory(dictionaryCategory.getId())
    }

    def "TestDeleteCategory NotFoundException case"() {
        given:
        def id = random.nextObject(Long)

        when:
        categoryService.deleteCategory(id)

        then:
        1 * categoryRepository.findById(id) >> Optional.empty()
        NotFoundException ex = thrown()
        ex.code == "CATEGORY_NOT_FOUND"
        ex.message == "No category with id (ID: %s) was found".formatted(id)
        0 * categoryRepository.deleteCategory(_)
    }

}
