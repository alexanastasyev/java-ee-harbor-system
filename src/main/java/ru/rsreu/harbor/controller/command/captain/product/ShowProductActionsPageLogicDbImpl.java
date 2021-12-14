package ru.rsreu.harbor.controller.command.captain.product;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.PierAssignment;

import java.util.Optional;

public class ShowProductActionsPageLogicDbImpl implements ShowProductActionsPageLogic {
    private final PierAssignmentDao pierAssignmentDao;
    private final UserDao userDao;
    private final RequestStatusDao requestStatusDao;

    public ShowProductActionsPageLogicDbImpl(
            PierAssignmentDao pierAssignmentDao, UserDao userDao, RequestStatusDao requestStatusDao) {
        this.pierAssignmentDao = pierAssignmentDao;
        this.userDao = userDao;
        this.requestStatusDao = requestStatusDao;
    }

    @Override
    public boolean isCaptainReadyForShowProductsActionsPage(String captainLogin) {
        Optional<PierAssignment> pierAssignmentOptional = this.pierAssignmentDao
                .findByCaptain(this.userDao.findByLogin(captainLogin).orElseThrow(IllegalArgumentException::new));
        return pierAssignmentOptional.isPresent() && pierAssignmentOptional
                .get()
                .getRequestStatus()
                .equals(this.requestStatusDao.findByTitle(Resourcer.getString("db.requestStatus.locked"))
                        .orElseThrow(IllegalArgumentException::new));
    }
}
