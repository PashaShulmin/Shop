package ru.itis.shop.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.shop.dto.ItemDto;
import ru.itis.shop.dto.ItemForm;
import ru.itis.shop.exceptions.AccountNotFoundException;
import ru.itis.shop.exceptions.ItemNotFoundException;
import ru.itis.shop.models.FileInfo;
import ru.itis.shop.mappers.ItemMapper;
import ru.itis.shop.models.Account;
import ru.itis.shop.models.Item;
import ru.itis.shop.repositories.AccountsRepository;
import ru.itis.shop.repositories.ItemsRepository;
import ru.itis.shop.services.ItemsService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemsServiceImpl implements ItemsService {
    private final ItemsRepository itemsRepository;
    private final ItemMapper itemMapper;
    private final FilesServiceImpl filesServiceImpl;
    private final AccountsRepository accountsRepository;

    public ItemDto saveItem(ItemForm itemForm) {
        FileInfo newFile = filesServiceImpl.saveFileToStorage(itemForm.getFile()).get();

        Item newItem = Item.builder()
                .title(itemForm.getTitle())
                .description(itemForm.getDescription())
                .price(Integer.parseInt(itemForm.getPrice()))
                .amount(Integer.parseInt(itemForm.getAmount()))
                .itemPictureStorageName(newFile.getStorageFileName())
                .build();

        itemsRepository.save(newItem);

        return itemMapper.toItemDto(newItem);
    }

    public ItemDto updateItem(Long itemId, ItemDto newData) {
        Item item = itemsRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        item.setTitle(newData.getTitle() == null ? item.getTitle() : newData.getTitle());
        item.setDescription(newData.getDescription() == null ? item.getDescription() : newData.getDescription());
        item.setPrice(newData.getPrice() == null ? item.getPrice() : newData.getPrice());
        item.setAmount(newData.getAmount() == null ? item.getAmount() : newData.getAmount());
        item.setItemPictureStorageName(newData.getItemPictureStorageName() == null ? item.getItemPictureStorageName() : newData.getItemPictureStorageName());

        itemsRepository.save(item);
        return itemMapper.toItemDto(item);
    }

    public List<ItemDto> getAllItems() {
        return itemMapper.toItemDtoList(itemsRepository.findAll());
    }

    public void addItemToFavourites(Long accountId, Long itemId) {
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        Item item = itemsRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);

        account.getFavorites().add(item);

        accountsRepository.save(account);
    }

    public List<ItemDto> getFavouriteItems(Long accountId) {
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        return itemMapper.toItemDtoList(account.getFavorites().stream().toList());
    }

    public void deleteItemFromFavourites(Long accountId, Long itemId) {
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        Item favouriteItem = itemsRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);

        account.getFavorites().remove(favouriteItem);

        accountsRepository.save(account);
    }

}
