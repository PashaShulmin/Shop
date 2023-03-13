package ru.itis.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.shop.dto.SignUpDto;
import ru.itis.shop.services.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;

    @GetMapping("/signUp")
    public String getSignUpPage(@ModelAttribute(name = "signUpDto") SignUpDto signUpDto, Authentication authentication) {
        if (authentication != null){
            return "redirect:/profile";
        }
        return "sign_up";
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute(name = "signUpDto")@Valid SignUpDto signUpDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sign_up";
        }
        signUpService.signUp(signUpDto);
        return "redirect:/signIn";
    }

    @GetMapping("/confirm/{confirmCode}")
    public String checkConfirmCode(@PathVariable("confirmCode") String confirmCode) {
        signUpService.checkConfirm(confirmCode);
        return "redirect:/signIn";
    }
}
