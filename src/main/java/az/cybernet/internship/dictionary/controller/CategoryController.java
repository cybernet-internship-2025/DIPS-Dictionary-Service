package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.model.category.CategoryDto;
import az.cybernet.internship.dictionary.model.category.CategoryWithItemsDto;
import az.cybernet.internship.dictionary.model.category.CategoryWithItemsResponse;
import az.cybernet.internship.dictionary.service.CategoryService;
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

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<CategoryWithItemsDto> getCategoryWithItems(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryWithItems(id));
    }
}
