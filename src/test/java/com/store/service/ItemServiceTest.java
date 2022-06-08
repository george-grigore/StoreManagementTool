package com.store.service;

import com.store.dto.ItemDto;
import com.store.entity.Item;
import com.store.exception.ItemException;
import com.store.repository.ItemRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    void when_getAllItems_then_callFindAll() {
        itemService.getAllItems();
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    void when_saveItem_then_callSave() {
        ItemDto itemDto = ItemDto.builder()
                .itemName("Banana")
                .itemPrice(6.0)
                .build();
        itemService.saveItem(itemDto);
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void when_saveItems_then_callSaveAll() {
        List<Item> itemList = Arrays.asList(Item.builder().build(), Item.builder().build());
        itemService.saveItems(itemList);
        verify(itemRepository, times(1)).saveAll(itemList);
    }

    @Test
    void when_findItemExist_then_returnItem() {
        Optional<Item> itemOptional = Optional.of(Item.builder().build());
        given(itemRepository.findById(any(Long.class))).willReturn(itemOptional);
        Item actualItem = itemService.findItem(0L);
        assertEquals(actualItem, itemOptional.get());
    }

    @Test
    void when_findItemNotExist_then_throwException() {
        Optional<Item> itemOptional = Optional.empty();
        given(itemRepository.findById(any(Long.class))).willReturn(itemOptional);
        assertThrows(ItemException.class, () -> itemService.findItem(0L));
    }

}