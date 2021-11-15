package ru.rsreu.harbor.controller.command.admin.edit;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.dto.DataTransferObject;
import ru.rsreu.harbor.datalayer.dao.RoleDao;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.model.User;

import javax.servlet.http.HttpServletRequest;

public class EditUserDataTransferObjectDbImpl implements DataTransferObject<User> {
    private final RoleDao roleDao;
    private final StatusDao statusDao;

    public EditUserDataTransferObjectDbImpl(RoleDao roleDao, StatusDao statusDao) {
        this.roleDao = roleDao;
        this.statusDao = statusDao;
    }

    @Override
    public User formModel(HttpServletRequest request) {
        return new User(
                Long.valueOf(request.getParameter(Resourcer.getString("user.form.dto.id"))),
                request.getParameter(Resourcer.getString("user.form.dto.login")),
                request.getParameter(Resourcer.getString("user.form.dto.password")),
                this.roleDao.findById(Long.valueOf(request.getParameter(Resourcer.getString("user.form.dto.role")))),
                this.statusDao.findById(Long.valueOf(request.getParameter(Resourcer.getString("user.form.dto.status"))))
        );
    }
}
