package ru.itis.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.shop.dto.AccountDto;
import ru.itis.shop.dto.SignUpDto;
import ru.itis.shop.exceptions.AccountAlreadyExistException;
import ru.itis.shop.exceptions.AccountNotFoundException;
import ru.itis.shop.util.EmailUtil;
import ru.itis.shop.mappers.AccountMapper;
import ru.itis.shop.Account;
import ru.itis.shop.repositories.AccountsRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SignUpService {
    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailUtil emailUtil;
    private final FilesService filesService;

    @Transactional
    public AccountDto signUp(SignUpDto signUpDto) {
        if (accountsRepository.findByEmail(signUpDto.getEmail()).isPresent()) {
            throw new AccountAlreadyExistException();
        } else {
            Account newAccount = Account.builder()
                    .firstName(signUpDto.getFirstName())
                    .lastName(signUpDto.getLastName())
                    .email(signUpDto.getEmail())
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .role(Account.Role.USER)
                    .state(Account.State.NOT_CONFIRMED)
                    .avatarStorageName(signUpDto.getFile().getSize() != 0 ? filesService.saveFileToStorage(signUpDto.getFile()).get().getStorageFileName() : "no_avatar.png")
                    .confirmCode(UUID.randomUUID().toString())
                    .build();

            accountsRepository.save(newAccount);

            Map<String, Object> templateData = new HashMap<>();
            templateData.put("first_name", newAccount.getFirstName());
            templateData.put("last_name", newAccount.getLastName());
            templateData.put("confirm_code", newAccount.getConfirmCode());
            templateData.put("email", newAccount.getEmail());

            emailUtil.sendMail(newAccount.getEmail(), "?????? ???????????????????? ?????????????????????? ?????????????? ???????????? ?? ????????????", "confirmMail.ftl", templateData);

            return accountMapper.toAccountDto(newAccount);
        }
    }

    public void checkConfirm(String confirmCode) {
        Account account = accountsRepository.findAccountByConfirmCode(confirmCode).orElseThrow(AccountNotFoundException::new);
        if (account.getState().equals(Account.State.NOT_CONFIRMED)) {
            account.setState(Account.State.CONFIRMED);
            accountsRepository.save(account);
        }
    }

}
