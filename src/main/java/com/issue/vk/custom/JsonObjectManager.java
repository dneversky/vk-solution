package com.issue.vk.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@Slf4j
public class JsonObjectManager {

    @InjectFirstArg
    private String pathToData;

    private final ObjectMapper mapper = new ObjectMapper();

    public <T> Object readValue(Class<T> classForRead) {
        try {
            return mapper.readValue(new File(pathToData), classForRead);
        } catch (IOException e) {
            log.error("Couldn't read from JSON datafile.", e);
            throw new RuntimeException(e);
        }
    }
}
