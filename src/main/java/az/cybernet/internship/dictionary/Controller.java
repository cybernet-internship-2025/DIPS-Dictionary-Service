package az.cybernet.internship.dictionary;

import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    Service service;

    @GetMapping("/api/v1/dictionaries")
    public List<String> getAllActive(String id, String value, boolean isActive, int limit) {
        return service.getList(id, value, isActive, limit);
    }

    @PutMapping("/api/v1/dictionaries")
    public void put(String id, String value, String description) {
        service.insert(id, value, description);
    }

    @DeleteMapping("/api/v1/dictionaries/${id}")
    public void delete() {
        service.delete(id);
    }

    @PostMapping("/api/v1/dictionaries/${id}/restore")
    public void restore() {
        service.restore(id);
    }
}
