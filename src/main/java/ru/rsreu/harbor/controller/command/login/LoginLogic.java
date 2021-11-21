package ru.rsreu.harbor.controller.command.login;

import ru.rsreu.harbor.datalayer.model.Role;

public interface LoginLogic {
    boolean checkLogin(String login, String password);

    Role getUserRole(String login);
}
