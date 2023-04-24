package ru.itis.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.shop.security.details.AccountUserDetails;
import ru.itis.shop.services.ItemsService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/favourites")
public class FavouritesController {
    private final ItemsService itemsService;

    @GetMapping
    public String getFavouritesPage(@AuthenticationPrincipal AccountUserDetails accountUserDetails, Model model) {
        model.addAttribute("favourites", itemsService.getFavouriteItems(accountUserDetails.getAccount().getId()));
        return "favourites";
    }
}
