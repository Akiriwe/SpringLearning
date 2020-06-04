package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.event.ConsoleEventLogger;

public class App {
    private ConsoleEventLogger consoleEventLogger;
    private Client client;

    public static void main(String[] args) {
        App app = new App();

        app.client = new Client("1", "John Smith");
        app.consoleEventLogger = new ConsoleEventLogger();

        app.logEvent("Some event for user 1");
    }

    private void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        consoleEventLogger.logEvent(message);
    }
}
