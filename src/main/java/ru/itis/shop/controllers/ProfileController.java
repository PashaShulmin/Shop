package ru.itis.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.shop.security.details.AccountUserDetails;
import ru.itis.shop.services.AccountsService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final AccountsService accountsService;

    @GetMapping
    public String getProfilePage(@AuthenticationPrincipal AccountUserDetails accountUserDetails, Model model) {
        model.addAttribute("user", accountUserDetails.getAccount());
        return "profile";
    }

    @PostMapping("/updateAvatar")
    public String updateAvatar(@AuthenticationPrincipal AccountUserDetails accountUserDetails, MultipartFile file) {
        accountsService.updateAvatar(accountUserDetails.getAccount().getId(), file, accountUserDetails);
        return "redirect:/profile";
    }


}
