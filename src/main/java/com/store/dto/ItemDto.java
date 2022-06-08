package com.store.dto;

import com.store.exception.ItemException;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {

    private String itemName;
    private Double itemPrice;

    public ItemDto(String itemName, Double itemPrice ) {
        if (itemName == null && itemPrice == null) {
            throw new ItemException("No value provided.");
        }
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
}
