package ru.rsreu.harbor.controller.command.admin;

import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public interface ShowAdminPageLogic {
    List<User> getAllUsers();
}
