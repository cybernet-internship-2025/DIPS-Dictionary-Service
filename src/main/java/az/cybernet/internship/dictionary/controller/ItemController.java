package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.model.request.ItemRequest;
import az.cybernet.internship.dictionary.model.response.ItemResponse;
import az.cybernet.internship.dictionary.service.abstraction.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequestMapping("api/v1/items")
public class ItemController {
    ItemService itemService;

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateItem(@PathVariable Long id, @RequestBody ItemRequest request) {
        itemService.updateItem(id, request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ItemResponse findById(@PathVariable Long id) {
        return itemService.findById(id);
    }

    @PutMapping("/{id}/restore")
    @ResponseStatus(NO_CONTENT)
    public void restore(@PathVariable Long id) {
        itemService.restoreItem(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItemById(id);
    }

    @PostMapping
    @ResponseStatus(OK)
    public void saveItem(@RequestBody @Valid ItemRequest request) {
        itemService.saveItem(request);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<ItemResponse> findAll(@RequestParam(required = false) Integer limit) {
        return itemService.findAllActiveItems(limit);
    }
}
