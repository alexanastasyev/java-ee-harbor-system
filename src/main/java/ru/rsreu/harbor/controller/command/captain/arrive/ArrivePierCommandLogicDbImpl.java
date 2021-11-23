package ru.rsreu.harbor.controller.command.captain.arrive;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.PierAssignment;

public class ArrivePierCommandLogicDbImpl implements ArrivePierCommandLogic {
    private final UserDao userDao;
    private final RequestStatusDao requestStatusDao;
    private final PierAssignmentDao pierAssignmentDao;

    public ArrivePierCommandLogicDbImpl(UserDao userDao, RequestStatusDao requestStatusDao, PierAssignmentDao pierAssignmentDao) {
        this.userDao = userDao;
        this.requestStatusDao = requestStatusDao;
        this.pierAssignmentDao = pierAssignmentDao;
    }

    @Override
    public void arrivePier(String captainLogin) {
        PierAssignment oldPierAssignment = this.pierAssignmentDao.findByCaptain(this.userDao.findByLogin(captainLogin));
        this.pierAssignmentDao.update(
                new PierAssignment(
                        oldPierAssignment.getId(),
                        oldPierAssignment.getPier(),
                        oldPierAssignment.getCaptain(),
                        this.requestStatusDao.findByTitle(Resourcer.getString("db.requestStatus.locked"))
                )
        );
    }
}
