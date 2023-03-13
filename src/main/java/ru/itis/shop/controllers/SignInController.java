package ru.itis.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.shop.services.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/signIn")
public class SignInController {

    @GetMapping
    public String getSignInPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/profile";
        }
        return "sign_in";
    }


}
