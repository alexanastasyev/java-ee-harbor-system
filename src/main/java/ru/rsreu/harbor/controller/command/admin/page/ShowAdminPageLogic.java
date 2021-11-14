package ru.rsreu.harbor.controller.command.admin.page;

import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public interface ShowAdminPageLogic {
    List<User> getAllUsers();
}
