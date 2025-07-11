package az.cybernet.internship.dictionary.controller;

import az.cybernet.internship.dictionary.model.request.ItemRequest;
import az.cybernet.internship.dictionary.model.response.ItemResponse;
import az.cybernet.internship.dictionary.service.abstraction.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

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
        itemService.restore(id);
    }

}
