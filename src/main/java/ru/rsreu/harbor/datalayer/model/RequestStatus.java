package ru.rsreu.harbor.datalayer.model;

public class RequestStatus {
    private final Long id;
    private final String title;

    public RequestStatus(Long id, String title) {
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
