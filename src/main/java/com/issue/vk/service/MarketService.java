package com.issue.vk.service;

import com.issue.vk.model.Book;
import com.issue.vk.model.BookForMarket;

import java.util.List;

public interface MarketService {

    List<Book> getAvailableBooks();

    BookForMarket getBookById(int bookId);

    void sellBook(BookForMarket bookToSell, int amount);
}
