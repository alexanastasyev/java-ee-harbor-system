package ru.rsreu.harbor.controller.command.admin.create;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.dto.DataTransferObject;
import ru.rsreu.harbor.datalayer.dao.RoleDao;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.model.User;

import javax.servlet.http.HttpServletRequest;

public class CreateUserDataTransferObjectDbImpl implements DataTransferObject<User> {
    private final RoleDao roleDao;
    private final StatusDao statusDao;

    public CreateUserDataTransferObjectDbImpl(RoleDao roleDao, StatusDao statusDao) {
        this.roleDao = roleDao;
        this.statusDao = statusDao;
    }

    @Override
    public User formModel(HttpServletRequest request) {
        return new User(
                null,
                request.getParameter(Resourcer.getString("user.form.dto.login")),
                request.getParameter(Resourcer.getString("user.form.dto.password")),
                roleDao.findById(Long.valueOf(request.getParameter(Resourcer.getString("user.form.dto.role")))),
                statusDao.findByTitle(Resourcer.getString("db.status.active"))
        );
    }
}
