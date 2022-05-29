package com.issue.vk.service.impl;

import com.issue.vk.model.Account;
import com.issue.vk.model.BookForAccount;
import com.issue.vk.model.BookForMarket;
import com.issue.vk.repository.AccountRepository;
import com.issue.vk.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount() {
        return accountRepository.getAccount();
    }

    public void buyBook(Account account, BookForMarket bookToBuy, int amount) {
        if(account.getMoney() < bookToBuy.getPrice() * amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sorry, you don't have enough money to buy.");
        }

        Optional<BookForAccount> foundBook = accountRepository.findBookById(bookToBuy.getId());

        if(foundBook.isPresent()) {
            BookForAccount bookForAccount = foundBook.get();
            bookForAccount.setAmount(bookForAccount.getAmount() + amount);
        } else {
            BookForAccount newBookForAccount = BookForAccount.buildBook(bookToBuy);
            newBookForAccount.setAmount(amount);
            account.getBooks().add(newBookForAccount);
        }

        account.setMoney(account.getMoney() - bookToBuy.getPrice() * amount);

        accountRepository.saveAccount(account);
    }
}
