package com.issue.vk.api;

import com.issue.vk.model.Account;
import com.issue.vk.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @GetMapping
    public ResponseEntity<Account> getAccount() {
        return ResponseEntity.ok().body(accountServiceImpl.getAccount());
    }
}
