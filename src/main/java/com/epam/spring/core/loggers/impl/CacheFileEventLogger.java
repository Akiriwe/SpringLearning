package com.epam.spring.core.loggers.impl;

import com.epam.spring.core.beans.Event;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class CacheFileEventLogger extends FileEventLogger {
    private final int cacheSize;
    private final List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<>(cacheSize);
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    @PreDestroy
    private void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

    private void writeEventsFromCache() {
        cache.forEach(super::logEvent);
    }
}
