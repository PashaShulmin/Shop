package ru.itis.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.shop.security.details.AccountUserDetails;
import ru.itis.shop.services.*;
import ru.itis.shop.services.PurchasesService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final OrdersService ordersService;
    private final PurchasesService purchasesService;

    @GetMapping("/buy")
    public String makeOrder(@AuthenticationPrincipal AccountUserDetails accountUserDetails) {
        ordersService.makeOrder(accountUserDetails.getAccount().getId());
        return "redirect:/cart";
    }

    @GetMapping
    public String getCartPage(@AuthenticationPrincipal AccountUserDetails accountUserDetails, Model model) {
        model.addAttribute("purchases", purchasesService.getAllCartPurchaseDtosByAccountId(accountUserDetails.getAccount().getId()));
        return "cart";
    }

    @GetMapping("/{purchase-id}/delete")
    public String deleteFromCart(@PathVariable("purchase-id") Long purchaseId) {
        purchasesService.deleteFromCart(purchaseId);
        return "redirect:/cart";
    }
}

