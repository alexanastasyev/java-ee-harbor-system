package ru.rsreu.harbor.controller.command.admin.user.edit;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.validation.UserModelValidator;
import ru.rsreu.harbor.controller.validation.UserModelValidatorDbImpl;
import ru.rsreu.harbor.controller.dto.DataTransferObject;
import ru.rsreu.harbor.datalayer.dao.RoleDao;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.User;

import javax.servlet.http.HttpServletRequest;

public class EditUserDataTransferObjectDbImpl implements DataTransferObject<User> {
    private final RoleDao roleDao;
    private final StatusDao statusDao;
    private final UserModelValidator userModelValidator;

    public EditUserDataTransferObjectDbImpl(UserDao userDao, RoleDao roleDao, StatusDao statusDao) {
        this.roleDao = roleDao;
        this.statusDao = statusDao;
        this.userModelValidator = new UserModelValidatorDbImpl(
                userDao,
                roleDao,
                statusDao);
    }

    @Override
    public User formModel(HttpServletRequest request) {
        String sessionLogin = request.getSession().getAttribute(
                Resourcer.getString("session.attribute.name.user")).toString();
        String idParameter = request.getParameter(Resourcer.getString("user.form.dto.id"));
        String login = request.getParameter(Resourcer.getString("user.form.dto.login"));
        String password = request.getParameter(Resourcer.getString("user.form.dto.password"));
        String roleIdParameter = request.getParameter(Resourcer.getString("user.form.dto.role"));
        String statusIdParameter = request.getParameter(Resourcer.getString("user.form.dto.status"));
        if (this.userModelValidator.isEditUserFormDataValid(
                sessionLogin, idParameter, login, password, roleIdParameter, statusIdParameter)) {
            return new User(
                    Long.valueOf(idParameter),
                    login,
                    password,
                    this.roleDao.findById(Long.valueOf(roleIdParameter)).orElseThrow(IllegalArgumentException::new),
                    this.statusDao.findById(Long.valueOf(statusIdParameter)).orElseThrow(IllegalArgumentException::new)
            );
        }
        throw new IllegalArgumentException();
    }
}
