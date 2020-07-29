package com.epam.spring.core.loggers.impl;

import com.epam.spring.core.beans.Event;
import com.epam.spring.core.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CombinedEventLogger implements EventLogger {
    @Autowired
    @Qualifier("listForCombinedLogger")
    private List<EventLogger> loggers;

    @Override
    public void logEvent(Event event) {
        loggers.forEach(logger -> logger.logEvent(event));
    }
}
