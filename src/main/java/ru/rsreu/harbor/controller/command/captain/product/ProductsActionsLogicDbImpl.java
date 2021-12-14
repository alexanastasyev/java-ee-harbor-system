package ru.rsreu.harbor.controller.command.captain.product;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.PierAssignment;
import ru.rsreu.harbor.datalayer.model.User;

public class ProductsActionsLogicDbImpl implements ProductsActionsLogic {
    private final UserDao userDao;

    private final PierAssignmentDao pierAssignmentDao;

    private final RequestStatusDao requestStatusDao;

    public ProductsActionsLogicDbImpl(
            UserDao userDao,
            PierAssignmentDao pierAssignmentDao,
            RequestStatusDao requestStatusDao) {
        this.userDao = userDao;
        this.pierAssignmentDao = pierAssignmentDao;
        this.requestStatusDao = requestStatusDao;
    }

    @Override
    public boolean isCaptainReadyForProductsActions(String captainLogin) {
        User captain = this.userDao.findByLogin(captainLogin).orElseThrow(IllegalArgumentException::new);
        PierAssignment pierAssignment = this.pierAssignmentDao
                .findByCaptain(captain)
                .orElseThrow(IllegalArgumentException::new);
        return pierAssignment.getRequestStatus().equals(
                this.requestStatusDao.findByTitle(
                                Resourcer.getString("db.requestStatus.locked"))
                        .orElseThrow(IllegalArgumentException::new));
    }
}
