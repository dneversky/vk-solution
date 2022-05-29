package com.issue.vk.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProxyServiceTest {

    @Mock
    AccountService accountService;

    @Mock
    MarketService marketService;

    @InjectMocks
    ProxyService proxyService;

    @Test
    void doDeal() {
        proxyService.doDeal(1, 2);
        verify(accountService).getAccount();
        verify(marketService).getBookById(anyInt());
        verify(accountService).buyBook(any(), any(), anyInt());
        verify(marketService).sellBook(any(), anyInt());
    }
}