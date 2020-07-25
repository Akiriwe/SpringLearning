package com.epam.spring.core.beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    private final int id = new Random().nextInt();
    private final Date date;
    private final DateFormat dateFormat;
    private String msg;

    public Event(Date date, DateFormat dateFormat) {
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + dateFormat.format(date) +
                '}';
    }
}
