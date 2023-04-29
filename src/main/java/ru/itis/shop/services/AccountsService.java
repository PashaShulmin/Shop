package ru.itis.shop.services;

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


@RequiredArgsConstructor
@Service
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;
    private final FilesService filesService;

    public void deleteAccount(Long accountId) {
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        account.setState(Account.State.DELETED);

        accountsRepository.save(account);
    }

    public AccountDto getAllAccounts() {
        return null;
    }

    public void updateAvatar(Long accountId, MultipartFile file, AccountUserDetails accountUserDetails) {
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        FileInfo fileInfo = filesService.saveFileToStorage(file).get();

        account.setAvatarStorageName(fileInfo.getStorageFileName());

        accountsRepository.save(account);

        accountUserDetails.setAccount(account);

    }
}
