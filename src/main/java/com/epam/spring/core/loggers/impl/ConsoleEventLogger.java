package com.epam.spring.core.loggers.impl;

import com.epam.spring.core.beans.Event;
import com.epam.spring.core.loggers.EventLogger;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
