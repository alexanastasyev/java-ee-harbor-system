package ru.rsreu.harbor.controller.validation;

import ru.rsreu.harbor.datalayer.model.RequestStatus;

public interface DispatcherApprovalValidator {
    boolean isDispatcherApprovalValid(RequestStatus currentStatus);
}
