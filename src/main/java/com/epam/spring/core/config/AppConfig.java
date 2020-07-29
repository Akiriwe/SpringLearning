package com.epam.spring.core.config;

import com.epam.spring.core.App;
import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import com.epam.spring.core.consts.EventType;
import com.epam.spring.core.loggers.EventLogger;
import com.epam.spring.core.loggers.impl.CombinedEventLogger;
import com.epam.spring.core.loggers.impl.ConsoleEventLogger;
import com.epam.spring.core.loggers.impl.FileEventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import java.text.DateFormat;
import java.util.*;

@Configuration
@Import(LoggerConfig.class)
@PropertySource("classpath:client.properties")
public class AppConfig {

    @Bean
    public Client client() {
        return new Client();
    }

    @Bean
    public App app() {
        return new App();
    }

    @Bean
    @Autowired
    public Map<EventType, EventLogger> eventLoggerMap(ConsoleEventLogger consoleEventLogger,
                                                      CombinedEventLogger combinedEventLogger) {
        Map<EventType, EventLogger> eventLoggerMap = new HashMap<>();
        eventLoggerMap.put(EventType.INFO, consoleEventLogger);
        eventLoggerMap.put(EventType.ERROR, combinedEventLogger);
        return eventLoggerMap;
    }

    @Bean
    @Autowired
    public List<EventLogger> listForCombinedLogger(ConsoleEventLogger consoleEventLogger,
                                                   FileEventLogger fileEventLogger) {
        List<EventLogger> eventLoggers = new ArrayList<>();
        eventLoggers.add(consoleEventLogger);
        eventLoggers.add(fileEventLogger);
        return eventLoggers;
    }

    @Bean
    @Scope(scopeName = "prototype")
    public Event event() {
        return new Event(new Date(), DateFormat.getDateTimeInstance());
    }
}
