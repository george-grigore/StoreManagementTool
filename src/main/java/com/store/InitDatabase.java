package com.store;

import com.store.entity.Item;
import com.store.service.ItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InitDatabase implements CommandLineRunner {

    private final ItemService itemService;

    public InitDatabase(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Item> itemList = Arrays.asList(
                Item.builder().itemName("Apple").itemPrice(4.5).build(),
                Item.builder().itemName("Pear").itemPrice(6).build(),
                Item.builder().itemName("Plums").itemPrice(3.2).build(),
                Item.builder().itemName("Peach").itemPrice(5).build());

        itemService.saveItems(itemList);
    }
}
