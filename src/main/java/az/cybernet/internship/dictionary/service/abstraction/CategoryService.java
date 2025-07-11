package az.cybernet.internship.dictionary.service.abstraction;

import az.cybernet.internship.dictionary.model.request.CategoryRequest;
import az.cybernet.internship.dictionary.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    void saveOrUpdateCategory(CategoryRequest request);

    CategoryResponse findById(Long id);

    void deleteCategory(Long id);

    List<CategoryResponse> findAll(Integer limit);

    void restoreCategory (Long id);
}
