package ru.itis.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.shop.dto.ItemForm;
import ru.itis.shop.dto.PurchaseForm;
import ru.itis.shop.security.details.AccountUserDetails;
import ru.itis.shop.services.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemsController {
    private final ItemsService itemsService;
    private final PurchasesService purchasesService;

    @GetMapping
    public String getItemsPage(@AuthenticationPrincipal AccountUserDetails accountUserDetails, Model model) {
        model.addAttribute("items", itemsService.getAllItems());
        model.addAttribute("user", accountUserDetails.getAccount());
        return "items";
    }

    @PostMapping("/addToCart")
    public String addToCart(@AuthenticationPrincipal AccountUserDetails accountUserDetails, PurchaseForm purchaseForm) {
        purchasesService.addToCart(purchaseForm, accountUserDetails.getAccount().getId());
        return "redirect:/cart";
    }

    @PostMapping("/addItem")
    public String addNewItem(ItemForm itemForm) {
        itemsService.saveItem(itemForm);
        return "redirect:/items";
    }

    @GetMapping("/add-to-favourites/{itemId}")
    public String addItemToFavourites(@AuthenticationPrincipal AccountUserDetails accountUserDetails, @PathVariable("itemId") Long itemId) {
        itemsService.addItemToFavourites(accountUserDetails.getAccount().getId(), itemId);
        return "redirect:/favourites";
    }

    @GetMapping("/delete-from-favourites/{itemId}")
    public String deleteItemFromFavourites(@AuthenticationPrincipal AccountUserDetails accountUserDetails, @PathVariable("itemId") Long itemId) {
        itemsService.deleteItemFromFavourites(accountUserDetails.getAccount().getId(), itemId);
        return "redirect:/favourites";
    }

}
