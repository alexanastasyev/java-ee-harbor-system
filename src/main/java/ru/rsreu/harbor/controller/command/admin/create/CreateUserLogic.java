package ru.rsreu.harbor.controller.command.admin.create;

import ru.rsreu.harbor.datalayer.model.User;

public interface CreateUserLogic {
    void createUser(User user);
}
