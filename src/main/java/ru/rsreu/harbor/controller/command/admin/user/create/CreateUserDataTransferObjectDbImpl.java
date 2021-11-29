package ru.rsreu.harbor.controller.command.admin.user.create;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.admin.user.validation.UserModelValidator;
import ru.rsreu.harbor.controller.command.admin.user.validation.UserModelValidatorDbImpl;
import ru.rsreu.harbor.controller.dto.DataTransferObject;
import ru.rsreu.harbor.datalayer.dao.RoleDao;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.User;

import javax.servlet.http.HttpServletRequest;

public class CreateUserDataTransferObjectDbImpl implements DataTransferObject<User> {
    private final static Long ACTIVE_STATUS_ID = 1L;

    private final RoleDao roleDao;
    private final StatusDao statusDao;
    private final UserModelValidator userModelValidator;


    public CreateUserDataTransferObjectDbImpl(UserDao userDao, RoleDao roleDao, StatusDao statusDao) {
        this.roleDao = roleDao;
        this.statusDao = statusDao;
        this.userModelValidator = new UserModelValidatorDbImpl(
                userDao,
                this.roleDao,
                this.statusDao
        );
    }

    @Override
    public User formModel(HttpServletRequest request) {
        String login = request.getParameter(Resourcer.getString("user.form.dto.login"));
        String password = request.getParameter(Resourcer.getString("user.form.dto.password"));
        String roleIdParameter = request.getParameter(Resourcer.getString("user.form.dto.role"));
        if (this.userModelValidator.isCreateUserFormValid(login, password, roleIdParameter, ACTIVE_STATUS_ID.toString())) {
            return new User(
                    null,
                    login,
                    password,
                    roleDao.findById(Long.valueOf(roleIdParameter)).orElseThrow(IllegalArgumentException::new),
                    statusDao.findById(ACTIVE_STATUS_ID).orElseThrow(IllegalArgumentException::new)
            );
        }
        throw new IllegalArgumentException();
    }
}
