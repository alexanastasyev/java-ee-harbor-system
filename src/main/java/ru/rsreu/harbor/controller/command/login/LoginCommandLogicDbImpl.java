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

    @Override
    public void setUserOnline(String login) {
        User user = userDao.findByLogin(login).orElseThrow(IllegalArgumentException::new);
        this.userDao.update(new User(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getRole(),
                user.getStatus(),
                true
        ));
    }
}
