package com.issue.vk.service;

import com.issue.vk.model.Book;
import com.issue.vk.model.BookForMarket;
import com.issue.vk.repository.MarketRepository;
import com.issue.vk.service.impl.MarketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarketServiceImplTest {

    @Mock
    MarketRepository repository;

    MarketServiceImpl service;

    List<BookForMarket> BOOK_STORAGE;

    @BeforeEach
    void init() {
        service = new MarketServiceImpl(repository);
        BOOK_STORAGE = new ArrayList<>();
        BOOK_STORAGE.add(new BookForMarket(1, "Mike J.", "Go lang", 1000, 10));
        BOOK_STORAGE.add(new BookForMarket(2, "Dr. Freeman", "Half-Life", 1500, 3));
        BOOK_STORAGE.add(new BookForMarket(3, "Christie Golden", "Illidan", 3000, 8));
    }

    @Test
    void getAvailableBooks() {
        when(repository.getBookStorage()).thenReturn(BOOK_STORAGE);
        List<Book> actualBooks = service.getAvailableBooks();
        assertEquals(BOOK_STORAGE, actualBooks);
    }

    @Test
    void getNotExistedBookById() {
        when(repository.getBookById(Mockito.anyInt())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> service.getBookById(1));
    }

    @Test
    void getExistedBookById() {
        when(repository.getBookById(Mockito.anyInt())).thenReturn(Optional.of(BOOK_STORAGE.get(0)));
        assertDoesNotThrow(() -> service.getBookById(1));
    }

    @Test
    void sellBook() {
        when(repository.getBookStorage()).thenReturn(BOOK_STORAGE);
        service.sellBook(BOOK_STORAGE.get(2), 2);
        assertEquals(6, BOOK_STORAGE.get(2).getAmount());
    }

    @Test
    void throwWhenNotEnoughBooksWhenSellBook() {
        assertThrows(ResponseStatusException.class, () -> service.sellBook(BOOK_STORAGE.get(0), 30));
    }
}