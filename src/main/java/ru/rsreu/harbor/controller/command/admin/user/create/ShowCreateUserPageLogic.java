package ru.rsreu.harbor.controller.command.admin.user.create;

import ru.rsreu.harbor.datalayer.model.Role;

import java.util.List;

public interface ShowCreateUserPageLogic {
    List<Role> getAllRoles();
}
