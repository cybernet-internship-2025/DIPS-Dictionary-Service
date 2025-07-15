package az.cybernet.internship.dictionary.service;


import az.cybernet.internship.dictionary.dto.category.create.CreateCategoryRequestBean;
import az.cybernet.internship.dictionary.dto.category.create.CreateCategoryResponseBean;
import az.cybernet.internship.dictionary.dto.category.deleteCategory.DeleteCategoryRequestBean;
import az.cybernet.internship.dictionary.dto.category.deleteCategory.DeleteCategoryResponseBean;
import az.cybernet.internship.dictionary.dto.category.restoreCategory.RestoreCategoryRequestBean;
import az.cybernet.internship.dictionary.dto.category.restoreCategory.RestoreCategoryResponseBean;
import az.cybernet.internship.dictionary.dto.category.updateCategory.UpdateCategoryRequestBean;
import az.cybernet.internship.dictionary.dto.category.updateCategory.UpdateCategoryResponseBean;
import az.cybernet.internship.dictionary.error.NotFoundException;
import az.cybernet.internship.dictionary.mapper.CategoryMapper;
import az.cybernet.internship.dictionary.mapstruct.CategoryMapstruct;
import az.cybernet.internship.dictionary.model.category.Category;
import az.cybernet.internship.dictionary.model.category.CategoryDto;
import az.cybernet.internship.dictionary.service.impl.CategoryService;
import az.cybernet.internship.dictionary.service.impl.DictionaryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CategoryServiceImpl implements CategoryService {

    CategoryMapper categoryMapper;
    CategoryMapstruct categoryMapstruct;
    DictionaryService dictionaryService;

    public List<CategoryDto> getAllCategories() {
        return categoryMapper.getAll();
    }

    public CategoryDto getCategoryWithItems(UUID categoryId) {
        return categoryMapper.getByIdWithItems(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + categoryId));
    }

    @Override
    @Transactional
    public UpdateCategoryResponseBean updateCategory(UpdateCategoryRequestBean request) {
        Category category=categoryMapper.findByIdWithoutDictionaries(request.getId())
                .orElseThrow(()-> new NotFoundException("Category not found!"));

        categoryMapstruct.fromUpdateCategoryRequestBeanToCategory(request,category);

        categoryMapper.update(category);

        UpdateCategoryResponseBean response = categoryMapstruct.fromCategoryToUpdateCategoryResponseBean(category);
        response.setMessage("Category updated successfully!");

        return response;
    }

    @Override
    public DeleteCategoryResponseBean deleteCategory(DeleteCategoryRequestBean request) {
        if(categoryMapper.findByIdWithoutDictionaries(request.getId()).isEmpty()){
            throw new NotFoundException("Category not found!");
        }

        categoryMapper.delete(request.getId());
        dictionaryService.changeActivityStatusByCategoryId(request.getId(), false);

        return DeleteCategoryResponseBean.builder()
                .message("Category deleted successfully!")
                .build();
    }

    @Override
    @Transactional
    public CreateCategoryResponseBean createCategory(CreateCategoryRequestBean request) {
        Category category = categoryMapstruct.fromCreateCategoryRequestBeanToCategory(request);

        if(request.getId() == null){
            category.setId(UUID.randomUUID());
        }

        categoryMapper.insert(category);

        CreateCategoryResponseBean response = categoryMapstruct.fromCategoryToCreateCategoryResponseBean(category);
        response.setMessage("Category created successfully!");

        return response;
    }

    @Override
    public RestoreCategoryResponseBean restoreCategory(RestoreCategoryRequestBean request) {

        Category category = categoryMapper.findByIdAndDisabled(request.getId())
                .orElseThrow(()->new NotFoundException("Dictionary not found!"));

        categoryMapper.restore(request.getId());
        dictionaryService.changeActivityStatusByCategoryId(request.getId(),true);

        RestoreCategoryResponseBean response=categoryMapstruct.fromCategoryToRestoreCategoryResponseBean(category);
        response.setMessage("Category restored successfully!");

        return response;
    }

}

