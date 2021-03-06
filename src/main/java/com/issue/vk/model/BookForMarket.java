package com.issue.vk.model;

public class BookForMarket extends Book {

    private double price;
    private int amount;

    public BookForMarket() {}

    public BookForMarket(int id, String author, String name, double price, int amount) {
        super(id, author, name);
        this.price = price;
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
