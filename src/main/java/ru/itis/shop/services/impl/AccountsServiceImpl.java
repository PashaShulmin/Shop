package ru.itis.shop.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.shop.dto.AccountDto;
import ru.itis.shop.exceptions.AccountNotFoundException;
import ru.itis.shop.models.FileInfo;
import ru.itis.shop.mappers.AccountMapper;
import ru.itis.shop.models.Account;
import ru.itis.shop.repositories.AccountsRepository;
import ru.itis.shop.security.details.AccountUserDetails;
import ru.itis.shop.services.AccountsService;
import ru.itis.shop.services.FilesService;


@RequiredArgsConstructor
@Service
public class AccountsServiceImpl implements AccountsService {
    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;
    private final FilesService filesService;

    @Override
    public void deleteAccount(Long accountId) {
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        account.setState(Account.State.DELETED);

        accountsRepository.save(account);
    }

    @Override
    public AccountDto getAllAccounts() {
        return null;
    }

    @Override
    public void updateAvatar(Long accountId, MultipartFile file, AccountUserDetails accountUserDetails) {
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        FileInfo fileInfo = filesService.saveFileToStorage(file).get();

        account.setAvatarStorageName(fileInfo.getStorageFileName());

        accountsRepository.save(account);

        accountUserDetails.setAccount(account);

    }
}
