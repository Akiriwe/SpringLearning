package com.epam.spring.core.loggers.impl;

import com.epam.spring.core.beans.Event;
import com.epam.spring.core.loggers.EventLogger;

import java.util.List;

public class CombinedEventLogger implements EventLogger {
    private final List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        loggers.forEach(logger -> logger.logEvent(event));
    }
}
