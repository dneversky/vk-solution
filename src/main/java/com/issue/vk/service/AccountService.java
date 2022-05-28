package com.issue.vk.service;

import com.issue.vk.model.Account;
import com.issue.vk.model.Book;
import com.issue.vk.model.BookForAccount;
import com.issue.vk.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccount() {
        return accountRepository.getAccount();
    }

    public void buyBook(Book bookToBuy, int amount, double price) {
        Account account = accountRepository.getAccount();
        List<BookForAccount> boughtBooks = account.getBooks();

        BookForAccount foundBook = null;

        for(BookForAccount book : boughtBooks) {
            if(book.getId() == bookToBuy.getId()) {
                foundBook = book;
            }
        }

        if(foundBook != null) {
            foundBook.setAmount(foundBook.getAmount() + amount);
        } else {
            BookForAccount newBookForAccount = new BookForAccount(
                    bookToBuy.getId(), bookToBuy.getAuthor(), bookToBuy.getName(), amount);
            boughtBooks.add(newBookForAccount);
        }

        double currentMoney = account.getMoney();
        account.setMoney(currentMoney - price * amount);

        accountRepository.saveAccount(account);
    }
}
