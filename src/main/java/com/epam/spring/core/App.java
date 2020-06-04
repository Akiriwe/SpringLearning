package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.loggers.EventLogger;

public class App {
    private EventLogger eventLogger;
    private Client client;

    public App(EventLogger eventLogger, Client client) {
        this.eventLogger = eventLogger;
        this.client = client;
    }

    public static void main(String[] args) {

    }

    private void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(message);
    }
}
