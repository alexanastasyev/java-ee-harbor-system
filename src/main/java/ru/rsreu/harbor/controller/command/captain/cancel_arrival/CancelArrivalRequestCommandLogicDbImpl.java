package ru.rsreu.harbor.controller.command.captain.cancel_arrival;

import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;

public class CancelArrivalRequestCommandLogicDbImpl implements CancelArrivalRequestCommandLogic {
    private final UserDao userDao;
    private final PierAssignmentDao pierAssignmentDao;

    public CancelArrivalRequestCommandLogicDbImpl(UserDao userDao, PierAssignmentDao pierAssignmentDao) {
        this.userDao = userDao;
        this.pierAssignmentDao = pierAssignmentDao;
    }

    @Override
    public void deleteRequestByCaptain(String captainLogin) {
        this.pierAssignmentDao.delete(this.pierAssignmentDao.findByCaptain(
                        this.userDao.findByLogin(captainLogin).orElseThrow(IllegalArgumentException::new))
                .orElseThrow(IllegalArgumentException::new));
    }
}