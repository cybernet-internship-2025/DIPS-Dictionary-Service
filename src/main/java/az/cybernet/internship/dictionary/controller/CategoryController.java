package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.model.request.CategoryRequest;
import az.cybernet.internship.dictionary.model.response.CategoryResponse;
import az.cybernet.internship.dictionary.service.abstraction.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;

    @PutMapping
    @ResponseStatus(NO_CONTENT)
    public void upsert(@RequestBody @Valid CategoryRequest request) {
        categoryService.saveOrUpdateCategory(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CategoryResponse findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<CategoryResponse> findAll(@RequestParam(required = false) Integer limit) {
        return categoryService.findAll(limit);
    }

    @PostMapping("/{id}")
    @ResponseStatus(OK)
    public void restoreCategory(@PathVariable Long id) {categoryService.restoreCategory(id);}
}
