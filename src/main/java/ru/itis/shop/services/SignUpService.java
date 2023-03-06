package ru.itis.shop.services;

import ru.itis.shop.dto.AccountDto;
import ru.itis.shop.dto.SignUpDto;

public interface SignUpService {
    AccountDto signUp(SignUpDto signUpDto);
    void checkConfirm(String confirmCode);
}
