package com.issue.vk.service;

import com.issue.vk.model.Account;
import com.issue.vk.model.BookForMarket;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommonService {

    private final MarketService marketService;
    private final AccountService accountService;

    public CommonService(MarketService marketService, AccountService accountService) {
        this.marketService = marketService;
        this.accountService = accountService;
    }

    public void doDeal(int bookId, int amount) {
        Account account = accountService.getAccount();
        BookForMarket bookToSell = marketService.getBookById(bookId);

        double needMoney = amount * bookToSell.getPrice();

        if(account.getMoney() < needMoney) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "You don't have enough money. You have only " + account.getMoney() + ", need " + needMoney + ".");
        }

        accountService.buyBook(account, bookToSell, amount);
        marketService.sellBook(bookToSell, amount);
    }
}
