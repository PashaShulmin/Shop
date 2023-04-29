package ru.itis.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.shop.security.details.AccountUserDetails;
import ru.itis.shop.services.OrdersService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping
    public String getOrdersPage(@AuthenticationPrincipal AccountUserDetails accountUserDetails, Model model) {
        model.addAttribute("orders", ordersService.getAccountOrders(accountUserDetails.getAccount().getId()));
        return "orders";
    }
}
