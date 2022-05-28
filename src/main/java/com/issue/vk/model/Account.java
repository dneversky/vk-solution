package com.issue.vk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    private double money;
    private List<BookForAccount> books = new ArrayList<>();

    public Account() {}

    public Account(double money, List<BookForAccount> books) {
        this.money = money;
        this.books = books;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public List<BookForAccount> getBooks() {
        return books;
    }

    public void setBooks(List<BookForAccount> books) {
        this.books = books;
    }
}
