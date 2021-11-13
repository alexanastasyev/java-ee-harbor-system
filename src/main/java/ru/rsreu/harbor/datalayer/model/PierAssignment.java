package ru.rsreu.harbor.datalayer.model;

public class PierAssignment {
    private final Long id;
    private final Pier pier;
    private final User captain;
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
