package ru.itis.shop.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.shop.dto.AccountDto;
import ru.itis.shop.security.details.AccountUserDetails;

public interface AccountsService {
    void deleteAccount(Long accountId);
    AccountDto getAllAccounts();
    void updateAvatar(Long accountId, MultipartFile file, AccountUserDetails accountUserDetails);
}