package ru.rsreu.harbor.datalayer.model;

/**
 * Class describing the essence a user report about a user
 */
public class Report {
    /**
     * Identifier of report
     */
    private final Long id;

    /**
     * What user the report came from
     */
    private final User fromUser;

    /**
     * What user the report came to
     */
    private final User toUser;

    /**
     * Text of report
     */
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
