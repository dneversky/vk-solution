package com.issue.vk.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonObjectManager {

    Logger log = LoggerFactory.getLogger(JsonObjectManager.class);

    @InjectFirstArg
    private String pathToData;

    private final ObjectMapper mapper = new ObjectMapper();

    public <T> Object readValue(Class<T> classForRead) {
        try {
            return mapper.readValue(new ClassPathResource(pathToData).getInputStream(), classForRead);
        } catch (IOException e) {
            log.error("Couldn't read from JSON datafile.");
            throw new RuntimeException(e);
        }
    }
}
