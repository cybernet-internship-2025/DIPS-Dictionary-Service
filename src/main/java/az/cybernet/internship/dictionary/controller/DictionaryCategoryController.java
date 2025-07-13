package az.cybernet.internship.dictionary.controller;
import az.cybernet.internship.dictionary.mapper.DictionaryCategoryMapper;
import az.cybernet.internship.dictionary.model.DictionaryCategory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
public class DictionaryCategoryController {

    private final DictionaryCategoryMapper categoryMapper;

    public DictionaryCategoryController(DictionaryCategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }


    @GetMapping
    public List<DictionaryCategory> getAllCategories() {
        return categoryMapper.getAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<DictionaryCategory> getCategoryById(@PathVariable UUID id) {
        DictionaryCategory category = categoryMapper.getById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }


    @PostMapping
    public ResponseEntity<DictionaryCategory> createCategory(@RequestBody DictionaryCategory category) {
        if (category.getName() == null || category.getName().isBlank()) {
            return ResponseEntity.badRequest().body(null);
        }

        category.setId(UUID.randomUUID());
        categoryMapper.insert(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DictionaryCategory> updateCategory(@PathVariable UUID id, @RequestBody DictionaryCategory category) {
        DictionaryCategory existing = categoryMapper.getById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        if (category.getName() == null || category.getName().isBlank()) {
            return ResponseEntity.badRequest().body(null);
        }
        category.setId(id);
        categoryMapper.update(category);
        return ResponseEntity.ok(category);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        DictionaryCategory existing = categoryMapper.getById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        categoryMapper.delete(id);
        return ResponseEntity.noContent().build();
    }
}
