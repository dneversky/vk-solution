package com.issue.vk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonObject {
    private Account account;
    private List<BookForMarket> books = new ArrayList<>();

    public JsonObject() {}

    public JsonObject(Account account, List<BookForMarket> books) {
        this.account = account;
        this.books = books;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<BookForMarket> getBooks() {
        return books;
    }

    public void setBooks(List<BookForMarket> books) {
        this.books = books;
    }
}
