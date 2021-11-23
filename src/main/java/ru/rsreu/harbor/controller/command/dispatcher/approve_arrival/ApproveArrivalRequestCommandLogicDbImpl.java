package ru.rsreu.harbor.controller.command.dispatcher.approve_arrival;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.PierDao;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.model.PierAssignment;

public class ApproveArrivalRequestCommandLogicDbImpl implements ApproveArrivalRequestCommandLogic {
    private final PierDao pierDao;
    private final RequestStatusDao requestStatusDao;
    private final PierAssignmentDao pierAssignmentDao;


    public ApproveArrivalRequestCommandLogicDbImpl(
            PierDao pierDao,
            RequestStatusDao requestStatusDao,
            PierAssignmentDao pierAssignmentDao
    ) {
        this.pierDao = pierDao;
        this.requestStatusDao = requestStatusDao;
        this.pierAssignmentDao = pierAssignmentDao;

    }

    @Override
    public void approveArrivalRequest(ArrivalRequestForm arrivalRequestForm) {
        PierAssignment oldPierAssignment = this.pierAssignmentDao.findById(arrivalRequestForm.getPierAssignmentId());
        this.pierAssignmentDao.update(
                new PierAssignment(
                        oldPierAssignment.getId(),
                        this.pierDao.findById(arrivalRequestForm.getPierId()),
                        oldPierAssignment.getCaptain(),
                        this.requestStatusDao.findByTitle(
                                Resourcer.getString("db.requestStatus.approved_arrival"))
                )
        );
    }
}
