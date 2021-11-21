package ru.rsreu.harbor.controller.command.login;

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
            User user = this.userDao.findByLogin(login);
            result = user.getLogin().equals(login) && user.getPassword().equals(password);
        } catch (NullPointerException e) {
            result = false;
        }
        return result;
    }

    public Role getUserRole(String login) {
        return userDao.findByLogin(login).getRole();
    }
}
