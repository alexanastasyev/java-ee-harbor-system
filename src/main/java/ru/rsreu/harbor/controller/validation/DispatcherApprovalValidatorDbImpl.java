package ru.rsreu.harbor.controller.validation;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.model.RequestStatus;

public class DispatcherApprovalValidatorDbImpl implements DispatcherApprovalValidator {
    private final RequestStatusDao requestStatusDao;

    public DispatcherApprovalValidatorDbImpl(RequestStatusDao requestStatusDao) {
        this.requestStatusDao = requestStatusDao;
    }

    @Override
    public boolean isDispatcherApprovalValid(RequestStatus currentStatus) {
        return currentStatus.equals(this.requestStatusDao
                .findByTitle(Resourcer.getString("db.requestStatus.requested_arrival"))
                .orElseThrow(IllegalArgumentException::new))
                || currentStatus.equals(this.requestStatusDao
                .findByTitle(Resourcer.getString("db.requestStatus.requested_department"))
                .orElseThrow(IllegalArgumentException::new));
    }
}
