package com.issue.vk.service;

import com.issue.vk.model.Account;
import com.issue.vk.model.BookForAccount;
import com.issue.vk.model.BookForMarket;
import com.issue.vk.repository.AccountRepository;
import com.issue.vk.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    AccountRepository repository;

    AccountServiceImpl service;

    Account ACCOUNT;
    List<BookForMarket> BOOK_STORAGE;

    @BeforeEach
    void beforeEach() {
        service = new AccountServiceImpl(repository);
        BOOK_STORAGE = new ArrayList<>();
        BOOK_STORAGE.add(new BookForMarket(1, "Mike J.", "Go lang", 1000, 10));
        BOOK_STORAGE.add(new BookForMarket(2, "Dr. Freeman", "Half-Life", 1500, 3));
        BOOK_STORAGE.add(new BookForMarket(3, "Christie Golden", "Illidan", 3000, 8));
        ACCOUNT = new Account(10000, new ArrayList<>());
    }

    @Test
    void getAccount() {
        when(repository.getAccount()).thenReturn(ACCOUNT);
        Account account = service.getAccount();
        assertEquals(ACCOUNT, account);
    }

    @Test
    void buyNotExistedBook() {
        BookForMarket book = BOOK_STORAGE.get(0);
        service.buyBook(ACCOUNT, book, 3);

        assertEquals(1, ACCOUNT.getBooks().size());
    }

    @Test
    void buyExistedBook() {
        BookForMarket bookToBuy = BOOK_STORAGE.get(1);
        BookForAccount bookForAccount = BookForAccount.buildBook(bookToBuy);
        bookForAccount.setAmount(2);
        ACCOUNT.getBooks().add(bookForAccount);

        when(repository.findBookById(anyInt())).thenReturn(Optional.of(bookForAccount));
        service.buyBook(ACCOUNT, bookToBuy, 1);

        assertEquals(3, ACCOUNT.getBooks().get(0).getAmount());
    }

    @Test
    void throwWhenBuyBookWithNotEnoughMoney() {
        assertThrows(ResponseStatusException.class, () -> service.buyBook(ACCOUNT, BOOK_STORAGE.get(2), 100));
    }

    @Test
    void moneyDecreasedAfterBuyBook() {
        service.buyBook(ACCOUNT, BOOK_STORAGE.get(2), 2);
        assertEquals(ACCOUNT.getMoney(), 4000);
    }
}