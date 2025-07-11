package az.cybernet.internship.dictionary.service.abstraction;

import az.cybernet.internship.dictionary.model.request.CategoryRequest;
import az.cybernet.internship.dictionary.model.response.CategoryResponse;

public interface CategoryService {

    void saveOrUpdateCategory(CategoryRequest request);

    CategoryResponse findById(Long id);

    void deleteCategory(Long id);

}
