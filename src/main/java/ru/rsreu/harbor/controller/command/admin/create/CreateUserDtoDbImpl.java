package ru.rsreu.harbor.controller.command.admin.create;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.RoleDao;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.model.User;

import javax.servlet.http.HttpServletRequest;

public class CreateUserDtoDbImpl implements CreateUserDto {
    private static final Long ACTIVE_STATUS_ID = 1L;

    private final RoleDao roleDao;
    private final StatusDao statusDao;

    public CreateUserDtoDbImpl(RoleDao roleDao, StatusDao statusDao) {
        this.roleDao = roleDao;
        this.statusDao = statusDao;
    }

    @Override
    public User formUser(HttpServletRequest request) {
        return new User(
                null,
                request.getParameter(Resourcer.getString("user.form.dto.login")),
                request.getParameter(Resourcer.getString("user.form.dto.password")),
                roleDao.findById(Long.valueOf(request.getParameter(Resourcer.getString("user.form.dto.role")))),
                statusDao.findById(ACTIVE_STATUS_ID)
        );
    }
}
