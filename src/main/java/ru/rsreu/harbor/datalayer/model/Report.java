package ru.rsreu.harbor.datalayer.model;

public class Report {
    private final Long id;
    private final User fromUser;
    private final User toUser;
    private final String text;

    public Report(Long id, User fromUser, User toUser, String text) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public User getFromUser() {
        return fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public String getText() {
        return text;
    }
}
