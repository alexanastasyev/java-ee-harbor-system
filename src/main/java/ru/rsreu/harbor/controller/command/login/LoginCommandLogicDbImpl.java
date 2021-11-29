package ru.rsreu.harbor.controller.command.login;

import ru.rsreu.harbor.controller.exception.LoginFaultException;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.User;

public class LoginCommandLogicDbImpl implements LoginCommandLogic {
    private final UserDao userDao;

    public LoginCommandLogicDbImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByLogin(String login) throws LoginFaultException {
        return this.userDao.findByLogin(login).orElseThrow(LoginFaultException::new);
    }

    @Override
    public boolean checkLogin(String verifiablePassword, String password) {
        return verifiablePassword.equals(password);
    }
}
