package com.store.service;

import com.store.dto.ItemDto;
import com.store.entity.Item;
import com.store.exception.ItemException;
import com.store.mapping.ItemMapper;
import com.store.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item saveItem(ItemDto itemDto) {
        Item item = ItemMapper.mapper.itemDtoToItem(itemDto);
        return itemRepository.save(item);
    }

    @Transactional
    public List<Item> saveItems(List<Item> itemList) {
        return itemRepository.saveAll(itemList);
    }

    public Item findItem(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            return optionalItem.get();
        }
        throw new ItemException("Item " + id + " not found!");
    }

    public List<Item> findItemByName(String itemName) {
        return itemRepository.findByItemNameContaining(itemName);
    }

    public Item updateItem(ItemDto itemDto, Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);

        if (itemOptional.isPresent()) {

            Item item = itemOptional.get();

            String newItemName = itemDto.getItemName();
            if (StringUtils.hasText(newItemName)) {
                item.setItemName(newItemName);
            }

            Double newItemPrice = itemDto.getItemPrice();
            if (newItemPrice != null) {
                item.setItemPrice(newItemPrice);
            }

            return itemRepository.save(item);
        }

        throw new ItemException("Item " + id + " not found!");
    }
}
