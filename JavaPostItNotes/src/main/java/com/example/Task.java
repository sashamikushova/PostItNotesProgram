package com.example;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Task {
    @Id
    private ObjectId id;
    private String body;
    private LocalDate date;
    private String time;

    public Task() {
    }

    public Task(ObjectId id, String body, LocalDate date, String time) {
        this.id = id;
        this.body = body;
        this.date = date;
        this.time = time;

    }

    public void setBody(String body) {

        this.body = body;
    }

    public void setDate(LocalDate date) {

        this.date = date;
    }

    public void setTime(String time) {
        try {
            LocalTime.parse(time);
            this.time = time;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format. Please provide time in the format HH:mm");
        }

    }

    public ObjectId getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}