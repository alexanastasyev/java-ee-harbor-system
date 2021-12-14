package ru.rsreu.harbor.controller.command.dispatcher.approve_department;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.validation.DispatcherApprovalValidator;
import ru.rsreu.harbor.controller.validation.DispatcherApprovalValidatorDbImpl;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.model.PierAssignment;

public class ApproveDepartmentRequestCommandLogicDbImpl implements ApproveDepartmentRequestCommandLogic {
    private final RequestStatusDao requestStatusDao;
    private final PierAssignmentDao pierAssignmentDao;

    private final DispatcherApprovalValidator dispatcherApprovalValidator;

    public ApproveDepartmentRequestCommandLogicDbImpl(
            RequestStatusDao requestStatusDao,
            PierAssignmentDao pierAssignmentDao
    ) {
        this.requestStatusDao = requestStatusDao;
        this.pierAssignmentDao = pierAssignmentDao;
        this.dispatcherApprovalValidator = new DispatcherApprovalValidatorDbImpl(this.requestStatusDao);
    }

    @Override
    public void approveDepartmentRequest(String pierAssignmentId) {
        PierAssignment oldPierAssignment =
                this.pierAssignmentDao.findById(Long.valueOf(pierAssignmentId))
                        .orElseThrow(IllegalArgumentException::new);
        if (dispatcherApprovalValidator.isDispatcherApprovalValid(oldPierAssignment.getRequestStatus())) {
            this.pierAssignmentDao.update(
                    new PierAssignment(
                            oldPierAssignment.getId(),
                            oldPierAssignment.getPier(),
                            oldPierAssignment.getCaptain(),
                            this.requestStatusDao.findByTitle(
                                    Resourcer.getString("db.requestStatus.approved_department")
                            ).orElseThrow(IllegalArgumentException::new)
                    )
            );
        } else {
            throw new IllegalArgumentException();
        }
    }
}
