package ru.itis.shop.mappers;

import org.mapstruct.Mapper;
import ru.itis.shop.dto.ItemDto;
import ru.itis.shop.Item;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item toItem(ItemDto itemDto);

    ItemDto toItemDto(Item item);

    List<ItemDto> toItemDtoList(List<Item> items);
}
