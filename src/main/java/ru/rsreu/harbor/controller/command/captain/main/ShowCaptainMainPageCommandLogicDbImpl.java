package ru.rsreu.harbor.controller.command.captain.main;

import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.PierAssignment;

public class ShowCaptainMainPageCommandLogicDbImpl implements ShowCaptainMainPageCommandLogic {
    private final UserDao userDao;
    private final PierAssignmentDao pierAssignmentDao;

    public ShowCaptainMainPageCommandLogicDbImpl(UserDao userDao, PierAssignmentDao pierAssignmentDao) {
        this.userDao = userDao;
        this.pierAssignmentDao = pierAssignmentDao;
    }

    @Override
    public PierAssignment getPierAssignmentByCaptain(String captainLogin) {
        return this.pierAssignmentDao.findByCaptain(
                this.userDao.findByLogin(captainLogin)
        );
    }
}
