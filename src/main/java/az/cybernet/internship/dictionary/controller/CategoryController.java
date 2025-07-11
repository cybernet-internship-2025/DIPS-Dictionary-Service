package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.model.category.CategoryDto;
import az.cybernet.internship.dictionary.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

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
}

