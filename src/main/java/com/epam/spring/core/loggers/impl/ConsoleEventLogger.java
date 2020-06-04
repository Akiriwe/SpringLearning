package com.epam.spring.core.loggers.impl;

import com.epam.spring.core.loggers.EventLogger;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(String msg) {
        System.out.println(msg);
    }
}
