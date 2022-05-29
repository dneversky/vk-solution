package com.issue.vk.service;

import com.issue.vk.model.Account;
import com.issue.vk.model.BookForMarket;
import org.springframework.stereotype.Service;

@Service
public class ProxyService {

    private final MarketService marketService;
    private final AccountService accountService;

    public ProxyService(MarketService marketService, AccountService accountService) {
        this.marketService = marketService;
        this.accountService = accountService;
    }

    public void doDeal(int bookId, int amount) {
        Account account = accountService.getAccount();
        BookForMarket bookToSell = marketService.getBookById(bookId);

        accountService.buyBook(account, bookToSell, amount);
        marketService.sellBook(bookToSell, amount);
    }
}
