package ru.rsreu.harbor.controller.command.dispatcher.approve_arrival;

public class ArrivalRequestForm {
    private final Long pierAssignmentId;
    private final Long pierId;

    public ArrivalRequestForm(Long pierAssignmentId, Long pierId) {
        this.pierAssignmentId = pierAssignmentId;
        this.pierId = pierId;
    }

    public Long getPierAssignmentId() {
        return pierAssignmentId;
    }

    public Long getPierId() {
        return pierId;
    }
}
