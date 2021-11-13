package ru.rsreu.harbor.datalayer.model;

public class Status {
    private final Long id;
    private final String title;

    public Status(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
