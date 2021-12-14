package ru.rsreu.harbor.datalayer.model;

/**
 * Class describing the relationship between pier, user and the status of the link
 */
public class PierAssignment {
    /**
     * Identifier of assignment
     */
    private final Long id;

    /**
     * Assignment pier
     */
    private final Pier pier;

    /**
     * Assignment user
     */
    private final User captain;

    /**
     * Assignment status
     */
    private final RequestStatus requestStatus;

    public PierAssignment(Long id, Pier pier, User captain, RequestStatus requestStatus) {
        this.id = id;
        this.pier = pier;
        this.captain = captain;
        this.requestStatus = requestStatus;
    }

    public Long getId() {
        return id;
    }

    public Pier getPier() {
        return pier;
    }

    public User getCaptain() {
        return captain;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }
}
