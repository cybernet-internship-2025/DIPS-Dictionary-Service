package az.cybernet.internship.dictionary.service.impl;

import az.cybernet.internship.dictionary.dto.category.create.CreateCategoryRequestBean;
import az.cybernet.internship.dictionary.dto.category.create.CreateCategoryResponseBean;
import az.cybernet.internship.dictionary.dto.category.deleteCategory.DeleteCategoryRequestBean;
import az.cybernet.internship.dictionary.dto.category.deleteCategory.DeleteCategoryResponseBean;
import az.cybernet.internship.dictionary.dto.category.restoreCategory.RestoreCategoryRequestBean;
import az.cybernet.internship.dictionary.dto.category.restoreCategory.RestoreCategoryResponseBean;
import az.cybernet.internship.dictionary.dto.category.updateCategory.UpdateCategoryRequestBean;
import az.cybernet.internship.dictionary.dto.category.updateCategory.UpdateCategoryResponseBean;

public interface CategoryService {

    UpdateCategoryResponseBean updateCategory(UpdateCategoryRequestBean request);
    DeleteCategoryResponseBean deleteCategory(DeleteCategoryRequestBean request);
    CreateCategoryResponseBean createCategory(CreateCategoryRequestBean request);
    RestoreCategoryResponseBean restoreCategory(RestoreCategoryRequestBean request);
}
