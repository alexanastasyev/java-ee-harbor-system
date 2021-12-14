package ru.rsreu.harbor.controller.command.captain.request_arrival;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.validation.CaptainActionValidator;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.PierAssignment;
import ru.rsreu.harbor.datalayer.model.RequestStatus;
import ru.rsreu.harbor.datalayer.model.User;

public class RequestArrivalCommandLogicDbImpl implements RequestArrivalCommandLogic {
    private final UserDao userDao;
    private final PierAssignmentDao pierAssignmentDao;
    private final RequestStatusDao requestStatusDao;

    private final CaptainActionValidator captainActionValidator;

    public RequestArrivalCommandLogicDbImpl(
            UserDao userDao,
            PierAssignmentDao pierAssignmentDao,
            RequestStatusDao requestStatusDao,
            CaptainActionValidator captainActionValidator) {
        this.userDao = userDao;
        this.pierAssignmentDao = pierAssignmentDao;
        this.requestStatusDao = requestStatusDao;
        this.captainActionValidator = captainActionValidator;
    }

    @Override
    public void requestArrival(String captainLogin) {
        User captain = this.userDao.findByLogin(captainLogin).orElseThrow(IllegalArgumentException::new);
        RequestStatus desiredStatus = this.requestStatusDao
                .findByTitle(Resourcer.getString("db.requestStatus.requested_arrival"))
                .orElseThrow(IllegalArgumentException::new);
        if (this.captainActionValidator.isCaptainActionValid(captain, desiredStatus)) {
            this.pierAssignmentDao.save(new PierAssignment(
                    null,
                    null,
                    captain,
                    desiredStatus
            ));
        } else {
            throw new IllegalArgumentException();
        }
    }
}
