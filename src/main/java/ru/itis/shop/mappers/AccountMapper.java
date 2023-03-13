package ru.itis.shop.mappers;

import org.mapstruct.Mapper;
import ru.itis.shop.dto.AccountDto;
import ru.itis.shop.dto.SignUpDto;
import ru.itis.shop.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(SignUpDto signUpDto);

    AccountDto toAccountDto(Account account);
}
