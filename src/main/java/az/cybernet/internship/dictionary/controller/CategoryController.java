package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.dto.category.create.CreateCategoryRequestBean;
import az.cybernet.internship.dictionary.dto.category.create.CreateCategoryResponseBean;
import az.cybernet.internship.dictionary.dto.category.deleteCategory.DeleteCategoryRequestBean;
import az.cybernet.internship.dictionary.dto.category.deleteCategory.DeleteCategoryResponseBean;
import az.cybernet.internship.dictionary.dto.category.restoreCategory.RestoreCategoryRequestBean;
import az.cybernet.internship.dictionary.dto.category.restoreCategory.RestoreCategoryResponseBean;
import az.cybernet.internship.dictionary.dto.category.updateCategory.UpdateCategoryRequestBean;
import az.cybernet.internship.dictionary.dto.category.updateCategory.UpdateCategoryResponseBean;
import az.cybernet.internship.dictionary.model.category.CategoryDto;
import az.cybernet.internship.dictionary.service.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    // GET /api/v1/categories
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // GET /api/v1/categories/{id}/items
    @GetMapping("/{id}/items")
    public ResponseEntity<CategoryDto> getCategoryWithItems(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.getCategoryWithItems(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdateCategoryResponseBean updateCategory(@RequestBody @Valid UpdateCategoryRequestBean request){
        return categoryService.updateCategory(request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public DeleteCategoryResponseBean deleteCategory(@RequestBody @Valid DeleteCategoryRequestBean request){
        return categoryService.deleteCategory(request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCategoryResponseBean createCategory(@RequestBody @Valid CreateCategoryRequestBean request){
        return categoryService.createCategory(request);
    }

    @PutMapping("/restore")
    @ResponseStatus(HttpStatus.OK)
    public RestoreCategoryResponseBean restoreCategory(@RequestBody @Valid RestoreCategoryRequestBean request){
        return categoryService.restoreCategory(request);
    }
}

