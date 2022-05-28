package com.issue.vk.service;

import com.issue.vk.model.Account;
import com.issue.vk.model.BookForMarket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommonService {

    @Autowired
    private MarketService marketService;

    @Autowired
    private AccountService accountService;

    public void doDeal(int bookId, int amount) {
        Account account = accountService.getAccount();
        BookForMarket bookToSell = marketService.getBookById(bookId);

        if(bookToSell.getAmount() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Market doesn't have enough books to sell.");
        }

        double needMoney = amount * bookToSell.getPrice();

        if(account.getMoney() < needMoney) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "You don't have enough money. You have only " + account.getMoney() + ", need " + needMoney + ".");
        }

        accountService.buyBook(bookToSell, amount, needMoney);
        marketService.sellBook(bookToSell, amount);
    }
}
