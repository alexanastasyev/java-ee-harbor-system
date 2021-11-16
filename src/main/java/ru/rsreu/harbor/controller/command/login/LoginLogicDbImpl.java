package ru.rsreu.harbor.controller.command.login;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.Role;
import ru.rsreu.harbor.datalayer.model.User;

public class LoginLogicDbImpl implements LoginLogic {
    private final UserDao userDao;

    public LoginLogicDbImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean checkLogin(String login, String password) {
        boolean result;
        try {
            User user = userDao.findByLogin(login);
            result = user.getLogin().equals(login) && user.getPassword().equals(password);
        } catch (NullPointerException e) {
            result = false;
        }
        return result;
    }

    @Override
    public String getUserPageCommand(String login) {
        String result;
        switch (this.getUserRole(login).getTitle()) {
            case "admin":
            {
                result = Resourcer.getString("command.path.showAdminPage");
                break;
            }
            case "moderator":
            {
                result = Resourcer.getString("command.path.showModeratorUsersPage");
                break;
            }
            default: {
                result = Resourcer.getString("command.path.showMainPage");
            }
        }
        return result;
    }

    private Role getUserRole(String login) {
        return userDao.findByLogin(login).getRole();
    }
}
