package com.issue.vk.model;

public class BookForAccount extends Book {

    private int amount;

    public BookForAccount() {}

    public BookForAccount(int id, String author, String name) {
        super(id, author, name);
    }

    public static BookForAccount buildBook(Book book) {
        return new BookForAccount(book.getId(), book.getAuthor(), book.getName());
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
