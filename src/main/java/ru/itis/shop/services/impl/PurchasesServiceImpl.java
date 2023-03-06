package ru.itis.shop.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.shop.dto.CartPurchaseDto;
import ru.itis.shop.dto.ItemDto;
import ru.itis.shop.dto.PurchaseForm;
import ru.itis.shop.exceptions.AccountNotFoundException;
import ru.itis.shop.exceptions.ItemNotFoundException;
import ru.itis.shop.exceptions.PurchaseNotFoundException;
import ru.itis.shop.models.Order;
import ru.itis.shop.dto.PurchaseDto;
import ru.itis.shop.mappers.ItemMapper;
import ru.itis.shop.mappers.PurchaseMapper;
import ru.itis.shop.models.Account;
import ru.itis.shop.models.Item;
import ru.itis.shop.models.Purchase;
import ru.itis.shop.repositories.AccountsRepository;
import ru.itis.shop.repositories.ItemsRepository;
import ru.itis.shop.repositories.PurchasesRepository;
import ru.itis.shop.services.PurchasesService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PurchasesServiceImpl implements PurchasesService {
    private final PurchasesRepository purchasesRepository;
    private final ItemsRepository itemsRepository;
    private final AccountsRepository accountsRepository;
    private final PurchaseMapper purchaseMapper;
    private final ItemMapper itemMapper;

    @Override
    public void addToCart(PurchaseForm purchaseForm, Long accountId) {
        Item item = itemsRepository.findById(purchaseForm.getItemId()).orElseThrow(ItemNotFoundException::new);
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        Purchase purchase = purchasesRepository.findByBuyerIdAndItemIdAndStatusEquals(accountId, purchaseForm.getItemId(), Purchase.Status.NOT_ORDERED);

        if (purchase != null) {
            purchase.setAmount(purchase.getAmount() + purchaseForm.getAmount());
            purchase.setPrice(purchase.getPrice() + item.getPrice() * purchaseForm.getAmount());

            purchasesRepository.save(purchase);
        } else {
            Purchase newPurchase = Purchase.builder()
                    .buyerId(account.getId())
                    .itemId(item.getId())
                    .price(item.getPrice() * purchaseForm.getAmount())
                    .amount(purchaseForm.getAmount())
                    .status(Purchase.Status.NOT_ORDERED)
                    .build();

            purchasesRepository.save(newPurchase);
        }

        item.setAmount(item.getAmount() - purchaseForm.getAmount());
        itemsRepository.save(item);

    }

    @Override
    public List<PurchaseDto> getAllAccountPurchases(Long accountId) {
        return (purchaseMapper.toPurchaseDtoList(purchasesRepository.findAllByBuyerId(accountId)));
    }

    @Override
    public void deleteFromCart(Long purchaseId) {
        Purchase purchase = purchasesRepository.findById(purchaseId).orElseThrow(PurchaseNotFoundException::new);
        Item item = itemsRepository.findById(purchase.getItemId()).orElseThrow(ItemNotFoundException::new);
        item.setAmount(item.getAmount() + purchase.getAmount());

        itemsRepository.save(item);
        purchasesRepository.deleteById(purchaseId);
    }

    @Override
    public List<ItemDto> getAllItemsByAccountId(Long accountId) {
        List<Purchase> purchases = purchasesRepository.findAllByBuyerId(accountId);
        List<Item> items = new ArrayList<>();
        for (Purchase purchase : purchases) {
            items.add(itemsRepository.findById(purchase.getItemId()).orElseThrow(ItemNotFoundException::new));
        }
        return itemMapper.toItemDtoList(items);
    }

    @Override
    public List<CartPurchaseDto> getAllCartPurchaseDtosByAccountId(Long accountId) {
        List<Purchase> purchases = purchasesRepository.findAllByBuyerId(accountId);
        List<CartPurchaseDto> purchaseDtos = new ArrayList<>();
        for (Purchase purchase : purchases) {
            CartPurchaseDto purchaseDto = CartPurchaseDto.builder()
                    .id(purchase.getId())
                    .buyerId(purchase.getBuyerId())
                    .amount(purchase.getAmount())
                    .price(purchase.getPrice())
                    .status(purchase.getStatus().toString())
                    .itemDto(itemMapper.toItemDto(itemsRepository.findById(purchase.getItemId()).orElseThrow(ItemNotFoundException::new)))
                    .build();

            purchaseDtos.add(purchaseDto);
        }

        return purchaseDtos;
    }

    @Override
    public List<CartPurchaseDto> getAllCartPurchaseDtosByOrder(Order order) {
        List<Purchase> purchases = purchasesRepository.findAllByOrder(order);
        List<CartPurchaseDto> purchaseDtos = new ArrayList<>();
        for (Purchase purchase : purchases) {
            CartPurchaseDto purchaseDto = CartPurchaseDto.builder()
                    .id(purchase.getId())
                    .buyerId(purchase.getBuyerId())
                    .amount(purchase.getAmount())
                    .price(purchase.getPrice())
                    .status(purchase.getStatus().toString())
                    .itemDto(itemMapper.toItemDto(itemsRepository.findById(purchase.getItemId()).orElseThrow(ItemNotFoundException::new)))
                    .build();

            purchaseDtos.add(purchaseDto);
        }

        return purchaseDtos;
    }
}
