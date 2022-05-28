package com.issue.vk.model;

public class BookForAccount extends Book {

    private int amount;

    public BookForAccount() {}

    public BookForAccount(int id, String author, String name, int amount) {
        super(id, author, name);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
