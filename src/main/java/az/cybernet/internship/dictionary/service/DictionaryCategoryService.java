package az.cybernet.internship.dictionary.service;

import az.cybernet.internship.dictionary.mapper.DictionaryCategoryMapper;
import az.cybernet.internship.dictionary.model.DictionaryCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DictionaryCategoryService {

    private final DictionaryCategoryMapper categoryMapper;

    public DictionaryCategoryService(DictionaryCategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public List<DictionaryCategory> getAllCategories() {
        return categoryMapper.getAll();
    }

    public DictionaryCategory getCategoryById(UUID id) {
        return categoryMapper.getById(id);
    }

    public DictionaryCategory createCategory(DictionaryCategory category) {

        category.setId(UUID.randomUUID());
        categoryMapper.insert(category);
        return category;
    }

    public DictionaryCategory updateCategory(UUID id, DictionaryCategory category) {
        DictionaryCategory existing = categoryMapper.getById(id);
        if (existing == null) {
            return null;
        }
        category.setId(id);
        categoryMapper.update(category);
        return category;
    }

    public boolean deleteCategory(UUID id) {
        DictionaryCategory existing = categoryMapper.getById(id);
        if (existing == null) {
            return false;
        }
        categoryMapper.delete(id);
        return true;
    }
}
