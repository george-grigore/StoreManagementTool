package com.store.controller;

import com.store.dto.ItemDto;
import com.store.entity.Item;
import com.store.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/store/item")
public class StoreController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreController.class);

    private final ItemService itemService;

    public StoreController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/find/all")
    public List<Item> getAllItems() {
        LOGGER.debug("Find all item.");
        return itemService.getAllItems();
    }

    @GetMapping("/find/{id}")
    public Item findItemById(@PathVariable Long id) {
        LOGGER.debug("Find item by id.");
        return itemService.findItem(id);
    }

    @GetMapping("/find")
    public List<Item> findItemByName(@RequestParam String itemName) {
        LOGGER.debug("Find item by name.");
        return itemService.findItemByName(itemName);
    }

    @PostMapping("/add")
    public Item addItem(@RequestBody ItemDto itemDto) {
        LOGGER.debug("Add a new item.");
        return itemService.saveItem(itemDto);
    }

    @PutMapping("/update/{id}")
    public Item updateItem(@RequestBody ItemDto itemDto, @PathVariable Long id) {
        LOGGER.debug("Update item by id.");
        return itemService.updateItem(itemDto, id);
    }
}
