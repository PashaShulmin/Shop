package ru.itis.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.shop.models.Account;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findAccountByConfirmCode(String confirmCode);
}
