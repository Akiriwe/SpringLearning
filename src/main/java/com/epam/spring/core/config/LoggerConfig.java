package com.epam.spring.core.config;

import com.epam.spring.core.loggers.EventLogger;
import com.epam.spring.core.loggers.impl.CacheFileEventLogger;
import com.epam.spring.core.loggers.impl.CombinedEventLogger;
import com.epam.spring.core.loggers.impl.ConsoleEventLogger;
import com.epam.spring.core.loggers.impl.FileEventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoggerConfig {
    public static final String FILE_NAME = "file-event-logger.log";
    public static final int CACHE_SIZE = 2;

    @Bean
    public ConsoleEventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean
    public FileEventLogger fileEventLogger() {
        return new FileEventLogger(FILE_NAME);
    }

    @Bean
    public CacheFileEventLogger cacheFileEventLogger() {
        return new CacheFileEventLogger(FILE_NAME, CACHE_SIZE);
    }

    @Bean
    public CombinedEventLogger combinedEventLogger() {
        return new CombinedEventLogger();
    }
}
