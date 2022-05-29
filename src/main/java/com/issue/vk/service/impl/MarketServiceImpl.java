package com.issue.vk.service.impl;

import com.issue.vk.model.Book;
import com.issue.vk.model.BookForMarket;
import com.issue.vk.repository.MarketRepository;
import com.issue.vk.service.MarketService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketServiceImpl implements MarketService {

    private final MarketRepository marketRepository;

    public MarketServiceImpl(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    public List<Book> getAvailableBooks() {
        return marketRepository.getBookStorage().stream()
                .filter(e -> e.getAmount() > 0)
                .collect(Collectors.toList());
    }

    public BookForMarket getBookById(int id) {
        return marketRepository.getBookById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find a book with id " + id + "."));
    }

    public void sellBook(BookForMarket bookToSell, int amount) {
        if(bookToSell.getAmount() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Market doesn't have enough books to sell.");
        }
        for(BookForMarket book : marketRepository.getBookStorage()) {
            if(bookToSell.getId() == book.getId()) {
                book.setAmount(book.getAmount() - amount);
            }
        }
    }
}
