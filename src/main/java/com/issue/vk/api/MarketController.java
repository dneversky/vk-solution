package com.issue.vk.api;

import com.issue.vk.model.Book;
import com.issue.vk.model.DealRequest;
import com.issue.vk.service.CommonService;
import com.issue.vk.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @Autowired
    private CommonService commonService;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok().body(marketService.getAvailableBooks());
    }

    @PostMapping("/deal")
    public ResponseEntity<?> doDeal(@RequestBody DealRequest dealRequest) {
        commonService.doDeal(dealRequest.getId(), dealRequest.getAmount());
        return ResponseEntity.ok().build();
    }
}
