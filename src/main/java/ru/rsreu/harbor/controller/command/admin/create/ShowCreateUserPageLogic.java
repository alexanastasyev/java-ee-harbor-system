package ru.rsreu.harbor.controller.command.admin.create;

import ru.rsreu.harbor.datalayer.model.Role;

import java.util.List;

public interface ShowCreateUserPageLogic {
    List<Role> getAllRoles();
}
