package az.cybernet.internship.dictionary.service

import az.cybernet.internship.dictionary.entity.DictionaryCategory
import az.cybernet.internship.dictionary.exception.NotFoundException
import az.cybernet.internship.dictionary.model.request.CategoryRequest
import az.cybernet.internship.dictionary.repository.CategoryMapper
import az.cybernet.internship.dictionary.service.abstraction.CategoryService
import az.cybernet.internship.dictionary.service.concrete.CategoryServiceImpl
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static az.cybernet.internship.dictionary.mapper.CategoryMapper.CATEGORY_MAPPER

class CategoryServiceTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    CategoryService categoryService
    CategoryMapper categoryMapper

    def setup() {
        categoryMapper = Mock()
        categoryService = new CategoryServiceImpl(categoryMapper)
    }

    // saveOrUpdateCategory
    def "TestSaveOrUpdateCategory id is null case"() {
        given:
        def categoryRequest = random.nextObject(CategoryRequest)
        categoryRequest.id = null
        def dictionaryCategory = random.nextObject(DictionaryCategory)

        CATEGORY_MAPPER.buildDictionaryCategory(categoryRequest) >> dictionaryCategory

        when:
        categoryService.saveOrUpdateCategory(categoryRequest)

        then:
        1 * categoryMapper.saveCategory(_ as DictionaryCategory)
    }

    def "TestSaveOrUpdateCategory id is not null success case"() {
        given:
        def categoryRequest = random.nextObject(CategoryRequest)
        def dictionaryCategory = random.nextObject(DictionaryCategory)

        CATEGORY_MAPPER.buildDictionaryCategory(categoryRequest) >> dictionaryCategory

        when:
        categoryService.saveOrUpdateCategory(categoryRequest)
        CATEGORY_MAPPER.updateCategory(dictionaryCategory, categoryRequest);

        then:
        1 * categoryMapper.findById(categoryRequest.getId()) >> Optional.of(dictionaryCategory)
        1 * categoryMapper.updateCategory(dictionaryCategory)
    }

    def "TestSaveOrUpdateCategory id is not null error case"() {
        given:
        def categoryRequest = random.nextObject(CategoryRequest)
        categoryRequest.id = 123L

        when:
        categoryService.saveOrUpdateCategory(categoryRequest)

        then:
        1 * categoryMapper.findById(categoryRequest.getId()) >> Optional.empty()
        NotFoundException ex = thrown()
        ex.getCode() == "CATEGORY_NOT_FOUND"
        ex.getMessage() == "No category with id (ID: %s) was found".formatted(categoryRequest.getId())
        0 * categoryMapper.updateCategory(_)
    }

    // restoreCategory
    def "TestRestoreCategory success case"() {
        given:
        def id = random.nextObject(Long)
        def dictionaryCategory = random.nextObject(DictionaryCategory)

        when:
        categoryService.restoreCategory(id)

        then:
        1 * categoryMapper.findById(id) >> Optional.of(dictionaryCategory)
        1 * categoryMapper.restoreCategory(dictionaryCategory.getId())
    }

    def "TestRestoreCategory NotFoundException case"() {
        given:
        def id = random.nextObject(Long)

        when:
        categoryService.restoreCategory(id)

        then:
        1 * categoryMapper.findById(id) >> Optional.empty()
        NotFoundException ex = thrown()
        ex.getCode() == "CATEGORY_NOT_FOUND"
        ex.getMessage() == "No category with id (ID: %s) was found".formatted(id)
        0 * categoryMapper.restoreCategory(_)
    }

    // findById
    def "TestFindById success case"() {
        given:
        def id = random.nextObject(Long)
        def dictionaryCategory = random.nextObject(DictionaryCategory)

        when:
        def categoryResponse = categoryService.findById(id)

        then:
        1 * categoryMapper.findById(id) >> Optional.of(dictionaryCategory)
        categoryResponse.id == dictionaryCategory.id
        categoryResponse.name == dictionaryCategory.name
        categoryResponse.description == dictionaryCategory.description
        categoryResponse.isActive == dictionaryCategory.isActive
        categoryResponse.createdAt == dictionaryCategory.createdAt
        categoryResponse.updatedAt == dictionaryCategory.updatedAt
    }

    def "TestFindById NotFoundException case"() {
        given:
        def id = random.nextObject(Long)

        when:
        categoryService.findById(id)

        then:
        1 * categoryMapper.findById(id) >> Optional.empty()
        NotFoundException ex = thrown()
        ex.code == "CATEGORY_NOT_FOUND"
        ex.message == "No category with id (ID: %s) was found".formatted(id)
    }

    // findAll
    def "TestFindAll"() {
        given:
        def limit = random.nextObject(Integer)
        def dictionaryCategory = random.nextObject(DictionaryCategory)

        when:
        def categoryResponses = categoryService.findAll(limit)
        def categoryResponse = CATEGORY_MAPPER.buildCategoryResponse(dictionaryCategory)

        then:
        1 * categoryMapper.findAll(limit) >> [dictionaryCategory]
        categoryResponse.id == dictionaryCategory.id
        categoryResponse.name == dictionaryCategory.name
        categoryResponse.description == dictionaryCategory.description
        categoryResponse.isActive == dictionaryCategory.isActive
        categoryResponse.createdAt == dictionaryCategory.createdAt
        categoryResponse.updatedAt == dictionaryCategory.updatedAt
        categoryResponses == [categoryResponse]
    }

    // deleteCategory
    def "TestDeleteCategory success case"() {
        given:
        def id = random.nextObject(Long)
        def dictionaryCategory = random.nextObject(DictionaryCategory)

        when:
        categoryService.deleteCategory(id)

        then:
        1 * categoryMapper.findById(id) >> Optional.of(dictionaryCategory)
        1 * categoryMapper.deleteCategory(dictionaryCategory.getId())
    }

    def "TestDeleteCategory NotFoundException case"() {
        given:
        def id = random.nextObject(Long)

        when:
        categoryService.deleteCategory(id)

        then:
        1 * categoryMapper.findById(id) >> Optional.empty()
        NotFoundException ex = thrown()
        ex.code == "CATEGORY_NOT_FOUND"
        ex.message == "No category with id (ID: %s) was found".formatted(id)
        0 * categoryMapper.deleteCategory(_)
    }

}
