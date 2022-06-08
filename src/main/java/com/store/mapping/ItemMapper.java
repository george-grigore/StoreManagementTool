package com.store.mapping;

import com.store.dto.ItemDto;
import com.store.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {

    ItemMapper mapper = Mappers.getMapper(ItemMapper.class);
    Item itemDtoToItem(ItemDto source);
}