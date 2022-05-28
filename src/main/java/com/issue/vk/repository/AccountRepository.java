package com.issue.vk.repository;

import com.issue.vk.custom.JsonObjectManager;
import com.issue.vk.model.Account;
import com.issue.vk.model.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AccountRepository {

    private Account account;

    @Autowired
    private JsonObjectManager jsonObjectManager;

    public Account getAccount() {
        return account;
    }

    public void saveAccount(Account account) {
        this.account = account;
    }

    @PostConstruct
    private void init() {
        JsonObject jsonObject = (JsonObject) jsonObjectManager.readValue(JsonObject.class);
        account = jsonObject.getAccount();
    }
}
