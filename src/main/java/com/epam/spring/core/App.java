package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import com.epam.spring.core.consts.EventType;
import com.epam.spring.core.loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private final Client client;
    private final EventLogger defaultEventLogger;
    private final Map<EventType, EventLogger> eventLoggerMap;

    public App(Client client, EventLogger defaultEventLogger, Map<EventType, EventLogger> eventLoggerMap) {
        this.client = client;
        this.defaultEventLogger = defaultEventLogger;
        this.eventLoggerMap = eventLoggerMap;

        System.out.println(client.getGreetings());
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        App app = applicationContext.getBean("app", com.epam.spring.core.App.class);


        Event event1 = applicationContext.getBean("event", com.epam.spring.core.beans.Event.class);
        Event event2 = applicationContext.getBean("event", com.epam.spring.core.beans.Event.class);
        Event event3 = applicationContext.getBean("event", com.epam.spring.core.beans.Event.class);

        event1.setMsg("Some event for user 1");
        event2.setMsg("Some event for user 2");
        event3.setMsg("Some event for user lol");

        app.logEvent(EventType.INFO, event1);
        app.logEvent(EventType.ERROR, event2);
        app.logEvent(null, event3);

        applicationContext.close();
    }

    private void logEvent(EventType eventType, Event event) {
        event.setMsg(event.getMsg().replaceAll(client.getId(), client.getFullName()));
        EventLogger eventLogger = eventLoggerMap.get(eventType);

        if (eventLogger == null) {
            eventLogger = defaultEventLogger;
        }

        eventLogger.logEvent(event);
    }
}
