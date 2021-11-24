package ru.rsreu.harbor.controller.command.captain.request_department;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.PierAssignment;

public class RequestDepartmentCommandLogicDbImpl implements RequestDepartmentCommandLogic {
    private final UserDao userDao;
    private final RequestStatusDao requestStatusDao;
    private final PierAssignmentDao pierAssignmentDao;

    public RequestDepartmentCommandLogicDbImpl(UserDao userDao, RequestStatusDao requestStatusDao, PierAssignmentDao pierAssignmentDao) {
        this.userDao = userDao;
        this.requestStatusDao = requestStatusDao;
        this.pierAssignmentDao = pierAssignmentDao;
    }


    @Override
    public void requestDepartment(String captainLogin) {
        PierAssignment oldPierAssignment = this.pierAssignmentDao.findByCaptain(
                        this.userDao.findByLogin(captainLogin).orElseThrow(IllegalArgumentException::new))
                .orElseThrow(IllegalArgumentException::new);
        this.pierAssignmentDao.update(
                new PierAssignment(
                        oldPierAssignment.getId(),
                        oldPierAssignment.getPier(),
                        oldPierAssignment.getCaptain(),
                        this.requestStatusDao.findByTitle(
                                        Resourcer.getString("db.requestStatus.requested_department"))
                                .orElseThrow(IllegalArgumentException::new)
                )
        );
    }
}
