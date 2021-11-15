package ru.rsreu.harbor.controller.command.admin.panel;

import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public interface ShowAdminPageLogic {
    List<User> getAllUsers();
}
