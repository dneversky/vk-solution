package com.issue.vk.api;

import com.issue.vk.model.Book;
import com.issue.vk.model.DealRequest;
import com.issue.vk.service.ProxyService;
import com.issue.vk.service.impl.MarketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketServiceImpl marketServiceImpl;

    @Autowired
    private ProxyService proxyService;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok().body(marketServiceImpl.getAvailableBooks());
    }

    @PostMapping("/deal")
    public ResponseEntity<?> doDeal(@RequestBody DealRequest dealRequest) {
        proxyService.doDeal(dealRequest.getId(), dealRequest.getAmount());
        return ResponseEntity.ok().build();
    }
}
