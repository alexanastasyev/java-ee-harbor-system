package ru.rsreu.harbor.controller.validation;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.model.PierAssignment;
import ru.rsreu.harbor.datalayer.model.RequestStatus;
import ru.rsreu.harbor.datalayer.model.User;

import java.util.Optional;

public class CaptainActionValidatorDbImpl implements CaptainActionValidator {
    private final RequestStatusDao requestStatusDao;

    private final PierAssignmentDao pierAssignmentDao;

    public CaptainActionValidatorDbImpl(RequestStatusDao requestStatusDao, PierAssignmentDao pierAssignmentDao) {
        this.requestStatusDao = requestStatusDao;
        this.pierAssignmentDao = pierAssignmentDao;
    }

    @Override
    public boolean isCaptainActionValid(User captain, RequestStatus desiredStatus) {
        boolean result;
        Optional<PierAssignment> pierAssignmentOptional = this.pierAssignmentDao.findByCaptain(captain);
        if (pierAssignmentOptional.isPresent()) {
            RequestStatus currentRequestStatus = pierAssignmentOptional.get().getRequestStatus();
            if (desiredStatus == null) {
                result = isDepartmentOrCancelArrivalActionValid(currentRequestStatus);
            } else {
                result = isArrivalOrCancelDepartmentActionValid(currentRequestStatus, desiredStatus)
                        || isRequestDepartmentActionValid(currentRequestStatus, desiredStatus);
            }

        } else {
            result = isRequestArrivalActionValid(desiredStatus);
        }
        return result;
    }

    private boolean isRequestArrivalActionValid(RequestStatus desiredStatus) {
        return desiredStatus.equals(this.getRequestStatusByTitle(
                Resourcer.getString("db.requestStatus.requested_arrival")));
    }

    private boolean isRequestDepartmentActionValid(RequestStatus currentRequestStatus, RequestStatus desiredStatus) {
        return desiredStatus.equals(this.getRequestStatusByTitle(
                Resourcer.getString("db.requestStatus.requested_department")))
                && currentRequestStatus.equals(this.getRequestStatusByTitle(
                Resourcer.getString("db.requestStatus.locked")));
    }

    private boolean isArrivalOrCancelDepartmentActionValid(
            RequestStatus currentRequestStatus, RequestStatus desiredStatus) {
        return desiredStatus.equals(this.getRequestStatusByTitle(
                Resourcer.getString("db.requestStatus.locked")))
                && (currentRequestStatus.equals(this.getRequestStatusByTitle(
                Resourcer.getString("db.requestStatus.approved_arrival")))
                || currentRequestStatus.equals(getRequestStatusByTitle(
                Resourcer.getString("db.requestStatus.requested_department"))));
    }

    private boolean isDepartmentOrCancelArrivalActionValid(RequestStatus currentRequestStatus) {
        return currentRequestStatus.equals(this.getRequestStatusByTitle(
                Resourcer.getString("db.requestStatus.requested_arrival")))
                || currentRequestStatus.equals(this.getRequestStatusByTitle(
                Resourcer.getString("db.requestStatus.approved_department")));
    }

    private RequestStatus getRequestStatusByTitle(String title) {
        return requestStatusDao.findByTitle(title).orElseThrow(IllegalArgumentException::new);
    }
}
