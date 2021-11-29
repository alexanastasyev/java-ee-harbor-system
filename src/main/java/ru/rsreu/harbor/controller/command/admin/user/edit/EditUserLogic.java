package ru.rsreu.harbor.controller.command.admin.user.edit;

import ru.rsreu.harbor.datalayer.model.User;

public interface EditUserLogic {
    void updateUser(User user);
}
