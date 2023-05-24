package com.journal.model;

import java.time.LocalDateTime;

public class Entry {

    private long id;
    private LocalDateTime whenCreated;
    private String entryText;

    public Entry(String noteText) {
        this.whenCreated = LocalDateTime.now();
    }

    public Entry() {}

    public long getId() { return id; }
    public LocalDateTime getWhenCreated() { return whenCreated; }
    public String getEntryText() { return entryText; }
    public void setId(long id) { this.id = id; }
    public void setWhenCreated(LocalDateTime whenCreated) { this.whenCreated = whenCreated; }
    public void setEntryText(String entryText) { this.entryText = entryText; }

    public String getStartOfEntry() {
        return entryText.substring(0, 30) + "...";
    }

    @Override
    public String toString() { return "Id: " + getId() + " Created on: " + getWhenCreated() + "Start of Entry: " + getStartOfEntry(); }
}
