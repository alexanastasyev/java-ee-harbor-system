package ru.rsreu.harbor.controller.command.captain.cancel_arrival;

import ru.rsreu.harbor.controller.validation.CaptainActionValidator;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.User;

public class CancelArrivalRequestCommandLogicDbImpl implements CancelArrivalRequestCommandLogic {
    private final UserDao userDao;
    private final PierAssignmentDao pierAssignmentDao;

    private final CaptainActionValidator captainActionValidator;

    public CancelArrivalRequestCommandLogicDbImpl(
            UserDao userDao,
            PierAssignmentDao pierAssignmentDao,
            CaptainActionValidator captainActionValidator) {
        this.userDao = userDao;
        this.pierAssignmentDao = pierAssignmentDao;
        this.captainActionValidator = captainActionValidator;
    }

    @Override
    public void deleteRequestByCaptain(String captainLogin) {
        User captain = this.userDao.findByLogin(captainLogin).orElseThrow(IllegalArgumentException::new);
        if (this.captainActionValidator.isCaptainActionValid(captain, null)) {
            this.pierAssignmentDao.delete(this.pierAssignmentDao.findByCaptain(
                            this.userDao.findByLogin(captainLogin).orElseThrow(IllegalArgumentException::new))
                    .orElseThrow(IllegalArgumentException::new));
        } else {
            throw new IllegalArgumentException();
        }
    }
}