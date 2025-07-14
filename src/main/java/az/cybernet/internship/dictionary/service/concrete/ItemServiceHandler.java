package az.cybernet.internship.dictionary.service.concrete;

import az.cybernet.internship.dictionary.entity.DictionaryCategory;
import az.cybernet.internship.dictionary.entity.DictionaryItem;
import az.cybernet.internship.dictionary.exception.NotFoundException;
import az.cybernet.internship.dictionary.mapper.ItemMapper;
import az.cybernet.internship.dictionary.model.request.ItemRequest;
import az.cybernet.internship.dictionary.model.response.ItemResponse;
import az.cybernet.internship.dictionary.repository.ItemRepository;
import az.cybernet.internship.dictionary.service.abstraction.CategoryService;
import az.cybernet.internship.dictionary.service.abstraction.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static az.cybernet.internship.dictionary.exception.ExceptionConstants.CATEGORY_NOT_FOUND;
import static az.cybernet.internship.dictionary.exception.ExceptionConstants.ITEM_NOT_FOUND;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ItemServiceHandler implements ItemService {
    ItemRepository itemRepository;
    CategoryService categoryService;
    ItemMapper itemMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateItem(Long id, ItemRequest request) {
        log.info("ActionLog.updateItem.start - id: {},  request: {}", id, request);
        var dictionaryItem = fetchDictionaryIfExist(id);

        if (request.getCategoryId() != null) {
            var dictionaryCategory = categoryService.fetchDictionaryIfExist(request.getCategoryId());
        }

        itemMapper.updateItem(dictionaryItem, request);
        itemRepository.updateItem(id, dictionaryItem);
        log.info("ActionLog.updateItem.end - id: {},  request: {}", id, request);
    }

    @Override
    public ItemResponse findById(Long id) {
        log.info("ActionLog.findByIdItem.start - id: {}", id);
        var dictionaryItem = fetchDictionaryIfExist(id);
        var itemResponse = itemMapper.buildItemResponse(dictionaryItem);
        log.info("ActionLog.findByIdItem.end - id: {}, response: {}", id, itemResponse);
        return itemResponse;
    }

    @Override
    public void restoreItem(Long id) {
        log.info("ActionLog.restoreItem.start - id: {}", id);
        var dictionaryItem = fetchDictionaryIfExist(id);
        itemRepository.restore(dictionaryItem.getId());
        log.info("ActionLog.restoreItem.end - id: {}", id);
    }

    @Override
    public List<ItemResponse> findAllActiveItems(Integer limit) {
        log.info("ActionLog.findAllItems.start");
        var dictionaryItems = itemRepository.findAll(limit);
        var list = dictionaryItems
                .stream()
                .map(itemMapper::buildItemResponse)
                .toList();
        log.info("ActionLog.findAllItems.end");
        return list;
    }

    @Override
    public void deleteItemById(Long id) {
        log.info("ActionLog.deleteItem.start - id: {}", id);
        var dictionaryItem = fetchDictionaryIfExist(id);
        itemRepository.deleteItem(dictionaryItem.getId());
        log.info("ActionLog.deleteItem.end - id: {}", id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveItem(ItemRequest request) {
        log.info("ActionLog.saveItem.start - request: {}", request);
        DictionaryCategory dictionaryCategory = categoryService
                .fetchDictionaryIfExist(request.getCategoryId());
        if (!dictionaryCategory.getIsActive()) {
            throw new NotFoundException(CATEGORY_NOT_FOUND.getCode(), ITEM_NOT_FOUND.getMessage());
        }
        var dictionaryItem = itemMapper.buildDictionaryItem(request, dictionaryCategory);
        itemRepository.saveItem(dictionaryItem);
        log.info("ActionLog.saveItem.end - request: {}", request);
    }

    private DictionaryItem fetchDictionaryIfExist(Long id) {
        return itemRepository.findById(id).orElseThrow(() ->
                new NotFoundException(ITEM_NOT_FOUND.getCode(), ITEM_NOT_FOUND.getMessage(id)));
    }
}
