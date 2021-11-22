package ru.rsreu.harbor.controller.command.login;

import ru.rsreu.harbor.datalayer.model.Role;
import ru.rsreu.harbor.datalayer.model.Status;

public interface LoginLogic {
    boolean checkLogin(String login, String password);

    Role getUserRole(String login);

    Status getUserStatus(String login);
}
