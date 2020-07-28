package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import com.epam.spring.core.config.AppConfig;
import com.epam.spring.core.config.LoggerConfig;
import com.epam.spring.core.consts.EventType;
import com.epam.spring.core.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class App {

    @Autowired
    private Client client;
    @Autowired
    @Qualifier("cacheFileEventLogger")
    private EventLogger defaultEventLogger;
    @Autowired
    private Map<EventType, EventLogger> eventLoggerMap;

    @PostConstruct
    public void init() {
        System.out.println(client.getGreetings());
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class, LoggerConfig.class);

        App app = applicationContext.getBean("app", com.epam.spring.core.App.class);


        Event infoEvent = applicationContext.getBean("event", com.epam.spring.core.beans.Event.class);
        Event errorEvent = applicationContext.getBean("event", com.epam.spring.core.beans.Event.class);
        Event defaultEvent = applicationContext.getBean("event", com.epam.spring.core.beans.Event.class);

        infoEvent.setMsg("Info event for user 1");
        errorEvent.setMsg("Error event for user 2");
        defaultEvent.setMsg("Default event for user lol");

        app.logEvent(EventType.INFO, infoEvent);
        app.logEvent(EventType.ERROR, errorEvent);
        app.logEvent(null, defaultEvent);

        applicationContext.close();
    }

    private void logEvent(EventType eventType, Event event) {
        event.setMsg(event.getMsg().replaceAll(client.getId(), client.getFullName()));
        EventLogger eventLogger = eventLoggerMap.get(eventType);

        if (eventLogger == null) {
            eventLogger = defaultEventLogger;
        }
        System.out.println("logEvent app method: " );
        System.out.println(eventLogger.getClass());
        System.out.println("log event app msg: " + event);

        eventLogger.logEvent(event);

        System.out.println(System.lineSeparator());
    }
}
