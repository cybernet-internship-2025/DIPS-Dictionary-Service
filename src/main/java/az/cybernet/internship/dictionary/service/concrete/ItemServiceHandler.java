package az.cybernet.internship.dictionary.service.concrete;

import az.cybernet.internship.dictionary.entity.DictionaryItem;
import az.cybernet.internship.dictionary.exception.NotFoundException;
import az.cybernet.internship.dictionary.model.request.ItemRequest;
import az.cybernet.internship.dictionary.model.response.ItemResponse;
import az.cybernet.internship.dictionary.repository.CategoryMapper;
import az.cybernet.internship.dictionary.repository.ItemMapper;
import az.cybernet.internship.dictionary.service.abstraction.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static az.cybernet.internship.dictionary.exception.ExceptionConstants.CATEGORY_NOT_FOUND;
import static az.cybernet.internship.dictionary.exception.ExceptionConstants.ITEM_NOT_FOUND;
import static az.cybernet.internship.dictionary.mapper.ItemMapper.ITEM_MAPPER;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ItemServiceHandler implements ItemService {
    ItemMapper itemMapper;
    CategoryMapper categoryMapper;

    @Override
    public void updateItem(Long id, ItemRequest request) {
        log.info("ActionLog.updateItem.start - id: {},  request: {}", id, request);
        var dictionaryItem = fetchDictionaryIfExist(id);

        if (request.getCategoryId() != null) {
            var dictionaryCategory = categoryMapper.findById(request.getCategoryId()).orElseThrow(() ->
                    new NotFoundException(CATEGORY_NOT_FOUND.getCode(), CATEGORY_NOT_FOUND.getMessage()));
        }

        ITEM_MAPPER.updateItem(dictionaryItem, request);
        itemMapper.updateItem(id, dictionaryItem);
        log.info("ActionLog.updateItem.end - id: {},  request: {}", id, request);
    }

    @Override
    public ItemResponse findById(Long id) {
        log.info("ActionLog.findByIdItem.start - id: {}", id);
        var dictionaryItem = fetchDictionaryIfExist(id);
        var itemResponse = ITEM_MAPPER.buildItemResponse(dictionaryItem);
        log.info("ActionLog.findByIdItem.end - id: {}, response: {}", id, itemResponse);
        return itemResponse;
    }

    @Override
    public void restore(Long id) {
        log.info("ActionLog.restoreItem.start - id: {}", id);
        var dictionaryItem = fetchDictionaryIfExist(id);
        itemMapper.restore(dictionaryItem.getId());
        log.info("ActionLog.restoreItem.end - id: {}", id);
    }

    private DictionaryItem fetchDictionaryIfExist(Long id) {
        return itemMapper.findById(id).orElseThrow(() ->
                new NotFoundException(ITEM_NOT_FOUND.getCode(), ITEM_NOT_FOUND.getMessage(id)));
    }
}
