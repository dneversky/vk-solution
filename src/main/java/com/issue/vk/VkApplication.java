package com.issue.vk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class VkApplication {

    static Logger log = LoggerFactory.getLogger(VkApplication.class);

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
