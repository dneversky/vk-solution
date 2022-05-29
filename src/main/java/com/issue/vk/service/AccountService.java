package com.issue.vk.service;

import com.issue.vk.model.Account;
import com.issue.vk.model.BookForMarket;

public interface AccountService {

    Account getAccount();

    void buyBook(Account account, BookForMarket bookToBuy, int quantity);
}
