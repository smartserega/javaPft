package ru.stqa.pft.rest;

public class Issue {

    private int id;
    private  int subject;
    private  int description;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public int getSubject() {
        return subject;
    }

    public Issue withSubject(int subject) {
        this.subject = subject;
        return this;
    }

    public int getDescription() {
        return description;
    }

    public Issue withDescription(int description) {
        this.description = description;
        return this;
    }
}
