package ru.rsreu.harbor.controller.command.captain.main;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.PierDao;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.Pier;
import ru.rsreu.harbor.datalayer.model.PierAssignment;

public class ShowCaptainMainPageCommandLogicDbImpl implements ShowCaptainMainPageCommandLogic {
    private final UserDao userDao;
    private final PierDao pierDao;
    private final RequestStatusDao requestStatusDao;
    private final PierAssignmentDao pierAssignmentDao;

    public ShowCaptainMainPageCommandLogicDbImpl(
            UserDao userDao,
            PierDao pierDao,
            RequestStatusDao requestStatusDao,
            PierAssignmentDao pierAssignmentDao
    ) {
        this.userDao = userDao;
        this.pierDao = pierDao;
        this.requestStatusDao = requestStatusDao;
        this.pierAssignmentDao = pierAssignmentDao;
    }

    @Override
    public PierAssignment getPierAssignmentByCaptain(String captainLogin) {
        return this.pierAssignmentDao.findByCaptain(
                        this.userDao.findByLogin(captainLogin).orElseThrow(IllegalArgumentException::new))
                .orElse(null);
    }

    @Override
    public boolean isFreePiers() {
        return this.pierDao.findAll().size() !=
                this.pierAssignmentDao
                        .findAll()
                        .stream()
                        .filter(it -> !it.getPier().equals(new Pier(-1L)))
                        .count();
    }

    @Override
    public boolean isArrivalRequestPierAssignmentStatus(PierAssignment pierAssignment) {
        if (pierAssignment == null) {
            return false;
        }
        return this.requestStatusDao.findByTitle(
                Resourcer.getString("db.requestStatus.requested_arrival")
        ).orElseThrow(IllegalArgumentException::new).getTitle().equals(pierAssignment.getRequestStatus().getTitle());
    }
}
