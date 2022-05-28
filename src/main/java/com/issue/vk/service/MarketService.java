package com.issue.vk.service;

import com.issue.vk.model.Book;
import com.issue.vk.model.BookForMarket;
import com.issue.vk.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketService {

    @Autowired
    private MarketRepository marketRepository;

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
        for(BookForMarket book : marketRepository.getBookStorage()) {
            if(bookToSell.getId() == book.getId()) {
                book.setAmount(book.getAmount() - amount);
            }
        }
    }
}
