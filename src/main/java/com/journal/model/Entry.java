package com.journal.model;

import java.sql.Timestamp;
import java.util.Date;

public class Entry {

    private long id;
    private Date whenCreated;
    private String entryText;
    private long idUser;

    public Entry(String noteText, long idUser) {
        this.whenCreated = new Date();
        this.entryText = noteText;
        this.idUser = idUser;
    }

    public Entry() {}

    public long getId() { return id; }
    public long getWhenCreated() { return whenCreated.getTime(); }
    public String getEntryText() { return entryText; }
    public long getIdUser() { return idUser; }
    public void setId(long id) { this.id = id; }
    public void setWhenCreated(Timestamp whenCreated) { this.whenCreated = whenCreated; }
    public void setEntryText(String entryText) { this.entryText = entryText; }
    public void setIdUser(long idUser) { this.idUser = idUser; }

    public String getStartOfEntry() {
        return entryText.substring(0, 30) + "...";
    }

    @Override
    public String toString() { return "Id: " + getId() + " Created on: " + getWhenCreated() + "Start of Entry: " + getStartOfEntry(); }
}
