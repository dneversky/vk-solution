package com.issue.vk.repository;

import com.issue.vk.custom.JsonObjectManager;
import com.issue.vk.model.BookForMarket;
import com.issue.vk.model.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Component
public class MarketRepository {

    private List<BookForMarket> bookStorage;

    @Autowired
    private JsonObjectManager jsonObjectManager;

    public Optional<BookForMarket> getBookById(int id) {
        return bookStorage.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    public List<BookForMarket> getBookStorage() {
        return bookStorage;
    }

    @PostConstruct
    private void init() {
        JsonObject jsonObject = (JsonObject) jsonObjectManager.readValue(JsonObject.class);
        bookStorage = jsonObject.getBooks();
        for(int i = 0; i < bookStorage.size(); i++) {
            bookStorage.get(i).setId(i + 1);
        }
    }
}
