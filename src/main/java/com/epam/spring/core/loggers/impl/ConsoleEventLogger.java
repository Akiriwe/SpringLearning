package com.epam.spring.core.loggers.impl;

import com.epam.spring.core.beans.Event;
import com.epam.spring.core.loggers.EventLogger;
import org.springframework.stereotype.Component;

@Component
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
