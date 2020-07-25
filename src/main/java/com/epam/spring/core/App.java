package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import com.epam.spring.core.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private final EventLogger eventLogger;
    private final Client client;

    public App(EventLogger eventLogger, Client client) {
        this.eventLogger = eventLogger;
        this.client = client;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        App app = applicationContext.getBean("app", com.epam.spring.core.App.class);

        Event event1 = applicationContext.getBean("event", com.epam.spring.core.beans.Event.class);
        Event event2 = applicationContext.getBean("event", com.epam.spring.core.beans.Event.class);

        event1.setMsg("Some event for user 1");
        event2.setMsg("Some event for user 2");

        app.logEvent(event1);
        app.logEvent(event2);
    }

    private void logEvent(Event event) {
        event.setMsg(event.getMsg().replaceAll(client.getId(), client.getFullName()));
        eventLogger.logEvent(event);
    }
}
