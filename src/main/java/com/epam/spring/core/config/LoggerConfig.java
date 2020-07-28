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
    @Autowired
    public List<EventLogger> listForCombinedLogger(ConsoleEventLogger consoleEventLogger,
                                                   FileEventLogger fileEventLogger) {
        List<EventLogger> eventLoggers = new ArrayList<>();
        eventLoggers.add(consoleEventLogger);
        eventLoggers.add(fileEventLogger);
        System.out.println("list " + eventLoggers);
        return eventLoggers;
    }

    @Bean
    @Autowired
    public CombinedEventLogger combinedEventLogger(List<EventLogger> listForCombinedLogger) {
        System.out.println("logger " + listForCombinedLogger);
        return new CombinedEventLogger(listForCombinedLogger);
    }
}
