package ru.rsreu.harbor.controller.command.captain.request_arrival;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.PierAssignment;

public class RequestArrivalCommandLogicDbImpl implements RequestArrivalCommandLogic {
    private final UserDao userDao;
    private final PierAssignmentDao pierAssignmentDao;
    private final RequestStatusDao requestStatusDao;

    public RequestArrivalCommandLogicDbImpl(UserDao userDao, PierAssignmentDao pierAssignmentDao, RequestStatusDao requestStatusDao) {
        this.userDao = userDao;
        this.pierAssignmentDao = pierAssignmentDao;
        this.requestStatusDao = requestStatusDao;
    }

    @Override
    public void requestArrival(String captainLogin) {
        this.pierAssignmentDao.save(new PierAssignment(
                null,
                null,
                this.userDao.findByLogin(captainLogin),
                this.requestStatusDao.findByTitle(Resourcer.getString("db.requestStatus.requested_arrival"))
        ));
    }
}
