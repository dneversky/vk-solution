package com.issue.vk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@Slf4j
@SpringBootApplication
public class VkApplication {

    public static void main(String[] args) {
        String logfile = "";
        try { logfile = args[1]; } catch (Exception ignored) {}
        new SpringApplicationBuilder(VkApplication.class)
                .properties("logging.file.name=" + logfile).run(args);
        if(logfile.isEmpty()) {
            log.warn("Log file hasn't been present. Logging will out just in a console.");
        }
    }
}
