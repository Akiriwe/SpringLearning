package com.epam.spring.core.loggers.impl;

import com.epam.spring.core.beans.Event;
import com.epam.spring.core.loggers.EventLogger;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class FileEventLogger implements EventLogger {
    private final String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    private void init() throws IOException {
        this.file = new File(fileName);
        file.createNewFile();

        if (!file.canWrite()) {
            throw new IOException("Cannot write to file - someone already uses it or you don't have permissions.");
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString().concat(System.lineSeparator()),
                    Charset.defaultCharset(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
