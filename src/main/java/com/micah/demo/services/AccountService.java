package com.micah.demo.services;

import com.micah.demo.entities.Account;
import com.micah.demo.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountInfo(String username) {
        Optional<Account> optionalAccount = accountRepository.findById(username);
        return optionalAccount.isEmpty() ? null : optionalAccount.get();
    }

    public Account login(String username) {
        Optional<Account> optionalAccount = accountRepository.findById(username);

        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        } else {
            Account account = new Account().setUsername(username).setBalance(0.0);
            return account;
        }
    }
}
