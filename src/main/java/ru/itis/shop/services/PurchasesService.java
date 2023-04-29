package ru.itis.shop.services;

import ru.itis.shop.dto.CartPurchaseDto;
import ru.itis.shop.dto.ItemDto;
import ru.itis.shop.dto.PurchaseDto;
import ru.itis.shop.dto.PurchaseForm;
import ru.itis.shop.models.Order;

import java.util.List;

public interface PurchasesService {
    void addToCart(PurchaseForm purchaseForm, Long accountId);
    List<PurchaseDto> getAllAccountPurchases(Long accountId);
    void deleteFromCart(Long purchaseId);
    List<ItemDto> getAllItemsByAccountId(Long accountId);
    List<CartPurchaseDto> getAllCartPurchaseDtosByAccountId(Long accountId);
    List<CartPurchaseDto> getAllCartPurchaseDtosByOrder(Order order);

}