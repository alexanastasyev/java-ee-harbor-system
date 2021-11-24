package ru.rsreu.harbor.controller.command.login;

import ru.rsreu.harbor.controller.exception.LoginFaultException;
import ru.rsreu.harbor.datalayer.model.User;

public interface LoginCommandLogic {
    User getUserByLogin(String login) throws LoginFaultException;

    boolean checkLogin(User user, String password);
}
