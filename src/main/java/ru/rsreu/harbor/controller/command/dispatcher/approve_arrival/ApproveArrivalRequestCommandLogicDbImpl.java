package ru.rsreu.harbor.controller.command.dispatcher.approve_arrival;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.validation.DispatcherApprovalValidator;
import ru.rsreu.harbor.controller.validation.DispatcherApprovalValidatorDbImpl;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.PierDao;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.model.PierAssignment;

public class ApproveArrivalRequestCommandLogicDbImpl implements ApproveArrivalRequestCommandLogic {
    private final PierDao pierDao;
    private final RequestStatusDao requestStatusDao;
    private final PierAssignmentDao pierAssignmentDao;

    private final DispatcherApprovalValidator dispatcherApprovalValidator;

    public ApproveArrivalRequestCommandLogicDbImpl(
            PierDao pierDao,
            RequestStatusDao requestStatusDao,
            PierAssignmentDao pierAssignmentDao
    ) {
        this.pierDao = pierDao;
        this.requestStatusDao = requestStatusDao;
        this.pierAssignmentDao = pierAssignmentDao;
        this.dispatcherApprovalValidator = new DispatcherApprovalValidatorDbImpl(this.requestStatusDao);
    }

    @Override
    public void approveArrivalRequest(ArrivalRequestForm arrivalRequestForm) {
        PierAssignment oldPierAssignment = this.pierAssignmentDao.findById(arrivalRequestForm.getPierAssignmentId())
                .orElseThrow(IllegalArgumentException::new);
        if (dispatcherApprovalValidator.isDispatcherApprovalValid(oldPierAssignment.getRequestStatus())) {
            this.pierAssignmentDao.update(
                    new PierAssignment(
                            oldPierAssignment.getId(),
                            this.pierDao.findById(arrivalRequestForm.getPierId()).orElseThrow(IllegalArgumentException::new),
                            oldPierAssignment.getCaptain(),
                            this.requestStatusDao.findByTitle(
                                            Resourcer.getString("db.requestStatus.approved_arrival"))
                                    .orElseThrow(IllegalArgumentException::new)
                    )
            );
        } else {
            throw new IllegalArgumentException();
        }
    }
}
