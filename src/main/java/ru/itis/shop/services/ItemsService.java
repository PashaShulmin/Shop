package ru.itis.shop.services;

import ru.itis.shop.dto.ItemDto;
import ru.itis.shop.dto.ItemForm;

import java.util.List;

public interface ItemsService {
    ItemDto saveItem(ItemForm itemForm);
    ItemDto updateItem(Long itemId, ItemDto newData);
    List<ItemDto> getAllItems();
    void addItemToFavourites(Long accountId, Long itemId);
    List<ItemDto> getFavouriteItems(Long accountId);
    void deleteItemFromFavourites(Long accountId, Long itemId);
}
